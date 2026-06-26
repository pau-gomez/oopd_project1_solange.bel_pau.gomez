package Presentation;

/**
 * Exception thrown when user input fails validation.
 */
public class InvalidInputException extends Exception {

    /**
     * Constructs an InvalidInputException with the given message.
     *
     * @param message description of the validation error
     */
    public InvalidInputException(String message) {
        super(message);
    }

    /**
     * Constructs an InvalidInputException with the given message and cause.
     *
     * @param message description of the validation error
     * @param cause the underlying exception that caused this error
     */
    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }
}