package org.date.dateconverter.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExceptionsHandlerTest {

    @Test
    void handleInternalServerError() {
        ExceptionsHandler handler = new ExceptionsHandler();
        ErrorResponse response = handler.handleInternalServerError(new RuntimeException("Internal Server Error"));
        assertEquals("Internal Server Error", response.getMessage());
    }

    @Test
    void handleBadRequestException() {
        ExceptionsHandler handler = new ExceptionsHandler();
        ErrorResponse response = handler.handleBadRequestException(new HttpMessageNotReadableException("Bad Request"));
        assertEquals("400 error, BAD REQUEST", response.getMessage());
    }

    @Test
    void handleMethodNotAllowed() {
        ExceptionsHandler handler = new ExceptionsHandler();
        ErrorResponse response = handler.handleMethodNotAllowed(new HttpRequestMethodNotSupportedException("Method Not Allowed"));
        assertEquals("405 error, METHOD NOT ALLOWED", response.getMessage());
    }

    @Test
    void handleNotFoundException() {
        ExceptionsHandler handler = new ExceptionsHandler();
        ErrorResponse response = handler.handleNotFoundException(new NoHandlerFoundException("Not Found", "GET", null));
        assertEquals("404 error, NOT FOUND", response.getMessage());
    }

    @Test
    void handleResponseStatusException() {
        ExceptionsHandler handler = new ExceptionsHandler();
        ResponseStatusException ex = new ResponseStatusException(HttpStatus.NOT_FOUND, "Custom Not Found");
        ErrorResponse response = handler.handleResponseStatusException(ex);
        assertEquals("Custom Not Found", response.getMessage());
    }
}
