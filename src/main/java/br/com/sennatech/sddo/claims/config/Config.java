package br.com.sennatech.sddo.claims.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.*;

@Configuration
public class Config {

  public static final String CONN_STRING = "AzureEventHubConnection";
  public static final String EVENT_HUB_NAME = "topico-sddo-usuarios";
  public static final Integer RECOVER_EXPIRATION_TIME = Integer.parseInt(System.getenv("EXPIRATION_TIME_IN_MINUTES"));
  public static final String RECOVERY_HOSTNAME = System.getenv("RECOVERY_HOSTNAME");
  public static final String EMAIL_FROM = System.getenv("EMAIL_FROM");
  
  @Bean
  public ModelMapper modelMapper() { 
    return new ModelMapper(); 
  }
}
