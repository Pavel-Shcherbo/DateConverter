package org.date.dateconverter.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ErrorResponseTest {

    @Test
    void getMessage_ShouldReturnCorrectMessage() {
        // Arrange
        String errorMessage = "Test error message";
        ErrorResponse errorResponse = new ErrorResponse(errorMessage);

        // Act
        String actualMessage = errorResponse.getMessage();

        // Assert
        assertEquals(errorMessage, actualMessage);
    }

    @Test
    void setMessage_ShouldSetCorrectMessage() {
        // Arrange
        String initialMessage = "Initial message";
        String newMessage = "New message";
        ErrorResponse errorResponse = new ErrorResponse(initialMessage);

        // Act
        errorResponse.setMessage(newMessage);
        String updatedMessage = errorResponse.getMessage();

        // Assert
        assertEquals(newMessage, updatedMessage);
    }
}
