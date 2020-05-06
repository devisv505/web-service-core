package com.devisv.core.exception;

import org.springframework.http.HttpStatus;

public class InternalSeverErrorException extends HttpException {

  public InternalSeverErrorException(String message) {
    this(message, null);
  }

  public InternalSeverErrorException(String message, Throwable cause) {
    super(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, cause);
  }

}
