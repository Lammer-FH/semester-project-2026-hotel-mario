package at.technikumwien.mse25.awt.hotelmario.common.exception;

public class InvalidDateRangeException extends RuntimeException {

    private final String field;

    public InvalidDateRangeException(String field, String message) {
        super(message);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
