package com.rakuten.productListings.ProductListings.model.response;

import org.springframework.http.HttpStatus;

import com.rakuten.productListings.ProductListings.model.response.Response.ResponseStatus;

public class ResponseFactory {

	public static Response createValidResponse() {
        return new Response(ResponseStatus.SUCCESS, null, null);
    }
	
	public static Response createValidResponse(final String message) {
        return new Response(ResponseStatus.SUCCESS, message, null);
    }
	
    public static Response createValidResponse(final Object data) {
        return new Response(ResponseStatus.SUCCESS, "Sucess", data);
    }

    public static Response createValidResponse(final Object data, final String message) {
        return new Response(ResponseStatus.SUCCESS, message, data);
    }

    public static Response createErrorResponse(final String message) {
    	return new Response(ResponseStatus.FAILURE, message, null);
    }

    public static Response createErrorResponse(final Throwable e) {
    	return new Response(ResponseStatus.FAILURE, e.getMessage(), null);
    }
}
