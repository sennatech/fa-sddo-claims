package br.com.sennatech.sddo.claims.handler;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.*;

import br.com.sennatech.sddo.claims.config.Config;
import br.com.sennatech.sddo.claims.domain.dto.DocumentDTO;
import br.com.sennatech.sddo.claims.domain.dto.event.EventDTO;
import br.com.sennatech.sddo.claims.domain.dto.util.ResponseDTO;
import br.com.sennatech.sddo.claims.service.DocumentService;
import br.com.sennatech.sddo.claims.util.LoggerUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SendDocumentHandler {

  private final DocumentService service;

  private static ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

  @FunctionName("send-document")
  public HttpResponseMessage run(
      @HttpTrigger(name = "req", methods = {
          HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS, route = "documents") HttpRequestMessage<String> request,
      @EventHubOutput(name = "event", eventHubName = Config.EVENT_HUB_NAME, connection = Config.CONN_STRING) OutputBinding<EventDTO> outputItem,
      final ExecutionContext context) {

    LoggerUtil logger = LoggerUtil.create(context, request);
    logger.logReq();

    try {
      var documentDTO = mapper.readValue(request.getBody(), DocumentDTO.class);
      service.create(documentDTO);
      outputItem.setValue(EventDTO.create(context, documentDTO));
      return request.createResponseBuilder(HttpStatus.ACCEPTED).build();
    } catch (EntityNotFoundException e) {
      logger.logError(e);
      return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body(ResponseDTO.create(e.getMessage())).build();
    } catch (Exception e) {
      logger.logError(e);
      return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).build();
    } finally {
      logger.info("Ending service");
    }
  }
}
