package exception;

public class InvalidPassword extends RuntimeException {
    public InvalidPassword() {
    }

    public InvalidPassword(String message) {
        super(message);
    }

    public InvalidPassword(String message, Throwable cause) {
        super(message, cause);
    }

}

