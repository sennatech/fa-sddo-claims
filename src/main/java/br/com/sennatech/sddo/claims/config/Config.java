package br.com.sennatech.sddo.claims.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.*;

@Configuration
public class Config {

  public static final String CONN_STRING = "AzureEventHubConnection";
  public static final String EVENT_HUB_NAME = "topico-sddo-usuarios";

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
