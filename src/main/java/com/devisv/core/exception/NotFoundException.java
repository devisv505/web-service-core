package com.devisv.core.exception;

import java.util.Map;
import org.springframework.http.HttpStatus;

public class NotFoundException extends HttpException {

  public NotFoundException(String message, Map<String, String> body) {
    super(HttpStatus.NOT_FOUND.value(), message, null, body);
  }
}
