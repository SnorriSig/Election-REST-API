package sem3.electionrestapi.exception;

import org.springframework.http.HttpStatus;

public class ElectionAPIException extends RuntimeException {

    private HttpStatus status;
    private String message;

    public ElectionAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ElectionAPIException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}