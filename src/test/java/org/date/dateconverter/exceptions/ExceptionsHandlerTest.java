package org.date.dateconverter.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExceptionsHandlerTest {

    @Test
    void handleInternalServerError() {
        // Arrange
        ExceptionsHandler handler = new ExceptionsHandler();
        RuntimeException ex = new RuntimeException("Internal Server Error");

        // Act
        ErrorResponse response = handler.handleInternalServerError(ex);

        // Assert
        assertEquals("Internal Server Error", response.getMessage());
    }

    @Test
    void handleMethodNotAllowed() {
        // Arrange
        ExceptionsHandler handler = new ExceptionsHandler();
        Exception ex = new HttpRequestMethodNotSupportedException("");

        // Act
        ErrorResponse response = handler.handleMethodNotAllowed(ex);

        // Assert
        assertEquals("405 error, METHOD NOT ALLOWED", response.getMessage());
    }

    @Test
    void handleNotFoundException() {
        // Arrange
        ExceptionsHandler handler = new ExceptionsHandler();
        Exception ex = new NoHandlerFoundException("", "", null);

        // Act
        ErrorResponse response = handler.handleNotFoundException(ex);

        // Assert
        assertEquals("404 error, NOT FOUND", response.getMessage());
    }

    @Test
    void handleResponseStatusException() {
        // Arrange
        ExceptionsHandler handler = new ExceptionsHandler();
        ResponseStatusException ex = new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");

        // Act
        ErrorResponse response = handler.handleResponseStatusException(ex);

        // Assert
        assertEquals("Not found", response.getMessage());
    }

    @Test
    void testHandleInternalServerError() {
        // Arrange
        ExceptionsHandler handler = new ExceptionsHandler();
        Exception ex = new RuntimeException("Internal Server Error");

        // Act
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> handler.handleInternalServerError((RuntimeException) ex));

        // Assert
        assertEquals("Internal Server Error", thrown.getMessage());
    }
}
