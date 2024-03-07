package com.c1639.backend.exception;

import java.time.ZonedDateTime;
import java.util.Map;

public record ApplicationExceptionResponse(
      Boolean isError,
      String dateTime,
      Integer statusCode,
      String path,
      Map<String, String> messages) {
}
