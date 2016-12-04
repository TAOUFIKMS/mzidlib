package uk.ac.liv.mzidlib.exceptions;

/**
 * Extending Exception to deal with FalseDiscoveryRateGlobal inputs
 */
public class FalseDiscoveryRateGlobalArgumentException extends Exception{
    /**
     * Cause message
     */
    private String message = null;

    /**
     * Constructor
     */
    public FalseDiscoveryRateGlobalArgumentException() {
        super();
    }

    /**
     * Constructor setting up the cause message
     * @param message
     */
    public FalseDiscoveryRateGlobalArgumentException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * Constructor with Throwable cause
     * @param cause
     */
    public FalseDiscoveryRateGlobalArgumentException(Throwable cause) {
        super(cause);
    }

    @Override
    /**
     * ToString returning cause message
     */
    public String toString() {
        return message;
    }

    @Override
    /**
     * Getter for cause message
     */
    public String getMessage() {
        return message;
    }
}
