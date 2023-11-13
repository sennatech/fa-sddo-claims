package br.com.sennatech.sddo.claims.handler;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.*;

import br.com.sennatech.sddo.claims.config.Config;
import br.com.sennatech.sddo.claims.domain.dto.ClaimDTO;
import br.com.sennatech.sddo.claims.domain.dto.event.EventDTO;
import br.com.sennatech.sddo.claims.domain.dto.util.ResponseDTO;
import br.com.sennatech.sddo.claims.service.ClaimService;
import br.com.sennatech.sddo.claims.util.LoggerUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NotificationHandler {

  private final ClaimService service;
  private final ObjectMapper mapper;

  @FunctionName("create-notification")
  public HttpResponseMessage run(
      @HttpTrigger(name = "req", methods = {
          HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS, route = "notification") HttpRequestMessage<String> request,
      @EventHubOutput(name = "event", eventHubName = Config.EVENT_HUB_NAME, connection = Config.CONN_STRING) OutputBinding<EventDTO> outputItem,
      final ExecutionContext context) throws InterruptedException {

    LoggerUtil logger = LoggerUtil.create(context, request);
    logger.logReq();

    try {
      ClaimDTO claimDTO = mapper.readValue(request.getBody(), ClaimDTO.class);
      service.create(claimDTO);
      outputItem.setValue(EventDTO.create(context, request.getBody()));
      Object refusalReasons = (service.getAutoRefusalReasons().isEmpty()) ? null : service.getAutoRefusalReasons();
      if (refusalReasons != null) logger.info("Auto refusal reasons: " + refusalReasons);
      return request.createResponseBuilder(HttpStatus.CREATED).build();
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
