package Persistance;

/**
 * Exception thrown when a persistence operation fails
 * (e.g. file not found, API unreachable, parse error).
 */
public class PersistenceException extends Exception {

    /**
     * Constructs a PersistenceException with the given message.
     *
     * @param message description of the error
     */
    public PersistenceException(String message) {
        super(message);
    }

    /**
     * Constructs a PersistenceException with the given message and cause.
     *
     * @param message description of the error
     * @param cause the underlying exception that caused this error
     */
    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}