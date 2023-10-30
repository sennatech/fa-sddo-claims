package br.com.sennatech.sddo.claims.domain.dto.event;

import java.text.SimpleDateFormat;
import java.util.*;

import com.microsoft.azure.functions.ExecutionContext;

import br.com.sennatech.sddo.claims.domain.enums.Operation;
import lombok.Data;

@Data
public class EventDTO {
  private Object data;
  private Operation operation;
  private String domain;
  private final String origin = System.getenv("origin");
  private String timestamp;

  private EventDTO(ExecutionContext context, Object... dataObjects) {
    if (dataObjects.length > 1) {
      this.data = dataObjects;
    } else {
      this.data = dataObjects[0];
    }
    this.domain = "Seguro";
    this.operation = Operation.get(context.getFunctionName());
    this.timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
  }

  public static EventDTO create(ExecutionContext context, Object... dataObjects) {
    return new EventDTO(context, dataObjects);
  }
}

