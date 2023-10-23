package br.com.sennatech.sddo.claims.exception;

public class InvalidClaimNotification extends RuntimeException {
  public InvalidClaimNotification(String message) {
    super(message);
  }
}