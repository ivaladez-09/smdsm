package com.uag.smdsm.security.exceptions;

import com.uag.smdsm.api.exceptions.ResourceNotFoundException;
import com.uag.smdsm.security.models.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDetails> handleBadCredentialsException(
            BadCredentialsException e,
            WebRequest webRequest) {

        final ErrorDetails errorDetails = createErrorDetails(e, webRequest);

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleUsernameNotFoundException(
            UsernameNotFoundException e,
            WebRequest webRequest) {

        final ErrorDetails errorDetails = createErrorDetails(e, webRequest);

        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(
            ResourceNotFoundException e,
            WebRequest webRequest) {

        final ErrorDetails errorDetails = createErrorDetails(e, webRequest);

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    private ErrorDetails createErrorDetails(Exception e, WebRequest webRequest) {
        return ErrorDetails.builder()
                .timestamp(LocalDate.now())
                .message(e.getMessage())
                .details(webRequest.getDescription(true))
                .build();
    }
}
