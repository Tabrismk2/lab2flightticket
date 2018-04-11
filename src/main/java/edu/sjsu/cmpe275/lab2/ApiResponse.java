package edu.sjsu.cmpe275.lab2;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;

public class ApiResponse {
    private HttpStatus status;
    private String message;

    public ApiResponse(HttpStatus status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    @JsonProperty("Response")
    public ObjectNode getResponse() {
        ObjectMapper mapper = new ObjectMapper();

        ObjectNode node = mapper.createObjectNode();
        node.put("code", this.status.value());
        node.put("msg", this.message);

        return node;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
