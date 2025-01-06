package com.zimaku.zimaku.exception.handler;

import com.zimaku.zimaku.exception.ResourceNotFoundException;
import com.zimaku.zimaku.exception.TokenNotFoundException;
import com.zimaku.zimaku.exception.UsernameAlreadyExistsException;
import com.zimaku.zimaku.exception.model.ErrorDetails;
import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TokenNotFoundException.class)
    public ResponseEntity<Object> handleTokenNotFoundException(TokenNotFoundException ex, @NonNull WebRequest webRequest){
        return errorDetailsResponse(ex, ex.getMessage(), Collections.singletonList(ex.getMessage()), webRequest, BAD_REQUEST);
    }

    @ExceptionHandler({UsernameAlreadyExistsException.class})
    public ResponseEntity<Object> handleTokenNotFoundException(UsernameAlreadyExistsException ex, @NonNull WebRequest webRequest){
        return errorDetailsResponse(ex, "Username is taken", Collections.singletonList(ex.getMessage()), webRequest, BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException ex, @NonNull WebRequest webRequest){
        return errorDetailsResponse(ex, ex.getMessage(), Collections.singletonList(ex.getMessage()), webRequest, BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, @NonNull WebRequest webRequest){
        return errorDetailsResponse(ex, ex.getMessage(), Collections.singletonList(ex.getMessage()), webRequest, UNAUTHORIZED);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, @NonNull WebRequest webRequest){
        return errorDetailsResponse(ex, ex.getMessage(), Collections.singletonList(ex.getMessage()), webRequest, HttpStatus.NOT_FOUND);
    }

    @Override
    protected @NonNull ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, @NonNull HttpHeaders headers,
                                                                           @NonNull HttpStatusCode status, @NonNull WebRequest webRequest) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.add(error.getDefaultMessage()));
        return errorDetailsResponse(ex, "Data sent contains invalid fields", errors, webRequest, BAD_REQUEST);
    }

    private ResponseEntity<Object> errorDetailsResponse(Exception exception, String exceptionMessage, List<String> errorsList, WebRequest webRequest, HttpStatus httpStatus){
        var response = new ErrorDetails(
                httpStatus.name(),
                httpStatus.value(),
                exceptionMessage,
                errorsList
        );

        return handleExceptionInternal(exception, response, new HttpHeaders(), httpStatus, webRequest);
    }
}
