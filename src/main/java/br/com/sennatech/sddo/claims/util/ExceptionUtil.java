package br.com.sennatech.sddo.claims.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ExceptionUtil {

  public String stackTraceToString(Exception e) {
    StringWriter sw = new StringWriter();
    e.printStackTrace(new PrintWriter(sw));
    return sw.toString();
  }
}
