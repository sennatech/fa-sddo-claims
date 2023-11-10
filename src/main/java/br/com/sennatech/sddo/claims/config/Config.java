package br.com.sennatech.sddo.claims.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class Config {

  public static final String CONN_STRING = "AzureEventHubConnection";
  public static final String EVENT_HUB_NAME = "topico-sddo-sinistros";

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper().registerModule(new JavaTimeModule());
  }
}
