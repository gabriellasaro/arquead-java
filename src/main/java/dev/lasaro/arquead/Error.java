package dev.lasaro.arquead;

public final class Error {
    private final String message;
    private final boolean err;

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
