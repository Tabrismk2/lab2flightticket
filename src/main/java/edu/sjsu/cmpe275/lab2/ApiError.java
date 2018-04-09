package edu.sjsu.cmpe275.lab2;

import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ApiError {
    private HttpStatus status;
    private String message;

    public ApiError(HttpStatus status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public ObjectNode getBadRequest() {
        ObjectMapper mapper = new ObjectMapper();

        ObjectNode errorNode = mapper.createObjectNode();
        errorNode.put("code", this.status.value());
        errorNode.put("msg", this.message);

        return errorNode;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}