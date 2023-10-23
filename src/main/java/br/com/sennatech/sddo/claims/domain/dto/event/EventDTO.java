package br.com.sennatech.sddo.claims.domain.dto.event;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.naming.OperationNotSupportedException;

import com.microsoft.azure.functions.ExecutionContext;

import br.com.sennatech.sddo.claims.domain.enums.Operation;
import lombok.Data;

@Data
public class EventDTO {
  private Object data;
  private Operation operation;
  private final String domain = "Seguro";
  private final String origin = System.getenv("origin");
  private String timestamp;

  private EventDTO(ExecutionContext context, Object... dataObjects) throws OperationNotSupportedException {
    if (dataObjects.length > 1) {
      this.data = dataObjects;
    } else {
      this.data = dataObjects[0];
    }
    this.operation = Operation.get(context.getFunctionName());
    this.timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
  }

  public static EventDTO create(ExecutionContext context, Object... dataObjects) throws OperationNotSupportedException {
    EventDTO event = new EventDTO(context, dataObjects);
    context.getLogger().info("Event created from template: " + event.toString());
    return event;
  }
}

