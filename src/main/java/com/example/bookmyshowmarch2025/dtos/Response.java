package com.example.bookmyshowmarch2025.dtos;

import lombok.Data;

@Data
public class Response {

    private ResponseStatus responseStatus;
    private String errorMessage;

    public Response(ResponseStatus responseStatus, String errorMessage) {
        this.responseStatus = responseStatus;
        this.errorMessage = errorMessage;
    }

    public static Response setSuccessResponse() {
        return new Response(ResponseStatus.SUCCESS, null);
    }

    public static Response setFailureResponse(String errorMessage) {
        return new Response(ResponseStatus.FAILURE, errorMessage);
    }
}
