package com.devisv.core.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

@JsonIgnoreProperties({"message", "localizedMessage", "stackTrace", "suppressed", "cause", "args", "messageKey", "stackTrace"})
public class HttpException extends RuntimeException  {

  @JsonProperty("status")
  private int status;

  @JsonProperty("exception")
  private String localizedMessage;

  @JsonProperty("body")
  @JsonInclude(Include.NON_NULL)
  private Map<String, String> body;

  public HttpException() {

  }

  public HttpException(int status, String message, Throwable cause, Map<String, String> body) {
    super(message, cause);
    this.status = status;
    this.localizedMessage = message;
    this.body = body;
  }

  public HttpException(int status, String message, Throwable cause) {
    super(message, cause);
    this.status = status;
    this.localizedMessage = message;
  }

  public Map<String, String> getBody() {
    return body;
  }

  public int getStatus() {
    return status;
  }

  @Override
  public String getLocalizedMessage() {
    return localizedMessage;
  }
}
