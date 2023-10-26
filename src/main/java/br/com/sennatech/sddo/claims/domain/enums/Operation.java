package br.com.sennatech.sddo.claims.domain.enums;

public enum Operation {
  CLAIM_NOTIFIED,
  CLAIMS_STATUS_UPDATED,
  DOCUMENT_SENT;

  public static Operation get(String functionName) {
    switch (functionName) {
      case "create-notification":
        return CLAIM_NOTIFIED;
      case "update-claim-status":
        return CLAIMS_STATUS_UPDATED;
      case "send-documents":
        return DOCUMENT_SENT;
      default:
        throw new UnsupportedOperationException("Non-existent operation: " + functionName);
    }
  }
}
