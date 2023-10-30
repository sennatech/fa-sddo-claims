package br.com.sennatech.sddo.claims.handler;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.*;

import br.com.sennatech.sddo.claims.service.ClaimDetails;
import br.com.sennatech.sddo.claims.util.LoggerUtil;

@Component
public class ClaimDetailsHandler {

  @Autowired
  private ClaimDetails service;

  private static ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

  @FunctionName("claim-details")
  public HttpResponseMessage run(
      @HttpTrigger(name = "req", methods = {
          HttpMethod.GET }, authLevel = AuthorizationLevel.ANONYMOUS, route = "{claimId}") HttpRequestMessage<String> request,
      @BindingName("claimId") String claimId,
      final ExecutionContext context) throws InterruptedException {

    LoggerUtil logger = LoggerUtil.create(context, request);
    logger.logReq();

    try {
      String response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(service.run(claimId));
      return request.createResponseBuilder(HttpStatus.OK).body(response).build();
    } catch (Exception e) {
      logger.logError(e);
      return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).build();
    } finally {
      logger.info("Ending service");
    }
  }
}
