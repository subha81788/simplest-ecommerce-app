package org.subhashis.simplestecommerceapp.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "timestamp", "status", "message", "errors" })
public class ApiError {
    @JsonProperty
    @JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    @JsonProperty
    private HttpStatus status;
    @JsonProperty
    private String message;
    @JsonProperty
    private List<String> errors;

    public ApiError(HttpStatus status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public ApiError(HttpStatus status, String message, LocalDateTime timestamp) {
        super();
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    public ApiError(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ApiError(HttpStatus status, String message, List<String> errors, LocalDateTime timestamp) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
        this.timestamp = timestamp;
    }

    public HttpStatus getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ApiError{");
        sb.append("timestamp=").append(timestamp);
        sb.append(", status=").append(status);
        sb.append(", message='").append(message).append('\'');
        sb.append(", errors=").append(errors);
        sb.append('}');
        return sb.toString();
    }
}
