package com.rakuten.productListings.ProductListings.model.response;

import org.springframework.http.HttpStatus;

public class Response {

	private ResponseStatus status; 
	private String message;
    private Object data;
    
    public static enum ResponseStatus {
        SUCCESS, FAILURE
    }
    
    public Response() {}
    
	public Response(ResponseStatus status, String message, Object data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}
	public ResponseStatus getStatus() {
		return status;
	}
	public void setStatus(ResponseStatus status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
