package br.com.sennatech.sddo.claims.handler;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.*;

import br.com.sennatech.sddo.claims.config.Config;
import br.com.sennatech.sddo.claims.domain.dto.EventClaimStatusDTO;
import br.com.sennatech.sddo.claims.domain.dto.StatusUpdateDTO;
import br.com.sennatech.sddo.claims.domain.dto.event.EventDTO;
import br.com.sennatech.sddo.claims.domain.dto.util.ResponseDTO;
import br.com.sennatech.sddo.claims.service.ClaimService;
import br.com.sennatech.sddo.claims.util.LoggerUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StatusChangeHandler {

  private final ClaimService service;
  private final ObjectMapper mapper;

  @FunctionName("update-claim-status")
  public HttpResponseMessage run(
      @HttpTrigger(name = "req", methods = {
          HttpMethod.PUT }, authLevel = AuthorizationLevel.ANONYMOUS, route = "status/{claimId}") HttpRequestMessage<StatusUpdateDTO> request,
      @BindingName("claimId") String claimId,
      @EventHubOutput(name = "event", eventHubName = Config.EVENT_HUB_NAME, connection = Config.CONN_STRING) OutputBinding<String> outputItem,
      final ExecutionContext context) {

    LoggerUtil logger = LoggerUtil.create(context, request);
    logger.logReq();

    try {
      EventClaimStatusDTO claim = service.updateStatus(claimId, request.getBody().getStatus());
      String event = mapper.writeValueAsString(EventDTO.create(context, claim));
      outputItem.setValue(event);
      return request.createResponseBuilder(HttpStatus.ACCEPTED).build();
    } catch (EntityNotFoundException | IllegalArgumentException e) {
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
