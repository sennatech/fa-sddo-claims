package br.com.sennatech.sddo.claims.domain.enums;

public enum Status {
  RECUSADO,
  PENDENTE,
  APROVADO;

  public static Status fromString(String status) {
    switch (status.toLowerCase()) {
      case "recusado":
        return RECUSADO;
      case "pendente":
        return PENDENTE;
      case "aprovado":
        return APROVADO;
      default:
        throw new IllegalArgumentException("Non-existent status");
    }
  }
}
