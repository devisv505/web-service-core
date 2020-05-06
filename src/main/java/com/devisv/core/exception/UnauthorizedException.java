package com.devisv.core.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends HttpException {

  public UnauthorizedException(String message) {
    this(message, null);
  }

  public UnauthorizedException(String message, Throwable cause) {
    super(HttpStatus.UNAUTHORIZED.value(), message, cause);
  }

}
