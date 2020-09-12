package dev.lasaro;

public final class Error {
    final private String message;
    final private boolean err;

    public Error() {
        this.message = "Success";
        this.err = false;
    }

    public Error(String message) {
        this.message = message;
        this.err = true;
    }

    public String toString() {
        return message;
    }

    public String getMessage() {
        return message;
    }

    public boolean isErr() {
        return err;
    }

    public boolean isSuccess() {
        return !err;
    }
}
