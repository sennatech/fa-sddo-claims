package br.com.sennatech.sddo.claims.exception;

public class DuplicatedEntityException extends RuntimeException {
  public DuplicatedEntityException(String message) {
    super(message);
  }
}
