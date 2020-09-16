package dev.lasaro.arquead;

public final class Result<T> {
    private T data;
    private Error error;

    public Result(T data) {
        this.data = data;
        this.error = new Error();
    }

    public Result(String message) {
        this.error = new Error(message);
    }

    public T getData() {
        return data;
    }

    public String toString() {
        return this.error.toString();
    }

    public boolean isError() {
        return this.error.isErr();
    }

    public boolean isSuccess() {
        return this.error.isSuccess();
    }

    public String getMessage() {
        return this.error.getMessage();
    }
}
