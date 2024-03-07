package com.c1639.backend.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;

import java.time.DateTimeException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class ExceptionUtils {

    public static ApplicationExceptionResponse createResponse(
      HttpStatus status,
      HttpServletRequest req,
      Map<String, String> errors ) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");
        ZonedDateTime now = ZonedDateTime.now();

        return new ApplicationExceptionResponse(
          true,
          now.format(formatter),
          status.value(),
          req.getRequestURI(),
          errors
        );

    }

}
