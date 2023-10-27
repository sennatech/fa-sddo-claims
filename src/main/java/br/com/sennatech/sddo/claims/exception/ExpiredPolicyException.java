package br.com.sennatech.sddo.claims.exception;

public class ExpiredPolicyException extends RuntimeException {
    
    public ExpiredPolicyException(String message) {
        super(message);
    }
}
