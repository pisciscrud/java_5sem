package by.stalmakhova.exception;

public class NonExistedUserException extends RuntimeException{
    public NonExistedUserException(String message) {
        super(message);
    }
}
