package br.com.sennatech.sddo.claims.handler;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Component;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.*;
import br.com.sennatech.sddo.claims.service.ListClaims;
import br.com.sennatech.sddo.claims.util.LoggerUtil;

@Component
public class ListClaimsHandler {

  @Autowired
  private ListClaims service;

  @FunctionName("list-claims")
  public HttpResponseMessage run(
      @HttpTrigger(name = "req", methods = {
          HttpMethod.GET}, authLevel = AuthorizationLevel.ANONYMOUS, route = "") HttpRequestMessage<String> request,
      final ExecutionContext context) throws InterruptedException {

    LoggerUtil logger = LoggerUtil.create(context, request);
    logger.logReq();

    try {
      return request.createResponseBuilder(HttpStatus.OK).body(service.run(request.getQueryParameters()).toString()).build();
    } catch (Exception e) {
      logger.logError(e);
      return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).build();
    } finally {
      logger.info("Ending service");
    }
  }
}
