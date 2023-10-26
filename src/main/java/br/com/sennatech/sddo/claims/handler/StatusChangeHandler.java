package br.com.sennatech.sddo.claims.handler;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Component;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.*;

import br.com.sennatech.sddo.claims.config.Config;
import br.com.sennatech.sddo.claims.domain.dto.StatusUpdateDTO;
import br.com.sennatech.sddo.claims.domain.dto.event.EventDTO;
import br.com.sennatech.sddo.claims.service.UpdateStatus;
import br.com.sennatech.sddo.claims.util.LoggerUtil;

@Component
public class StatusChangeHandler {

  @Autowired
  private UpdateStatus service;

  @FunctionName("update-claim-status")
  public HttpResponseMessage run(
      @HttpTrigger(name = "req", methods = {
          HttpMethod.PUT }, authLevel = AuthorizationLevel.ANONYMOUS, route = "status/{claimId}") HttpRequestMessage<StatusUpdateDTO> request,
      @BindingName("claimId") String claimId,
      @EventHubOutput(name = "event", eventHubName = Config.EVENT_HUB_NAME, connection = Config.CONN_STRING) OutputBinding<EventDTO> outputItem,
      final ExecutionContext context) throws InterruptedException {

    LoggerUtil logger = LoggerUtil.create(context, request);
    logger.logReq();

    try {
      service.run(claimId, request.getBody().getStatus());
      outputItem.setValue(EventDTO.create(context, claimId + " updated to " + request.getBody().getStatus()));
      return request.createResponseBuilder(HttpStatus.ACCEPTED).build();
    } catch (Exception e) {
      logger.logError(e);
      return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).build();
    } finally {
      logger.info("Ending service");
    }
  }
}
