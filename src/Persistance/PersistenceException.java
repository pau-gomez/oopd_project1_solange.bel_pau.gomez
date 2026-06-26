package Persistance;

/**
 * Exception thrown when a persistence operation fails
 * (e.g. file not found, API unreachable, parse error).
 */
public class PersistenceException extends Exception {

    public PersistenceException(String message) {
        super(message);
    }

    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}