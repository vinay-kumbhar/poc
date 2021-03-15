package com.neosoft.springbootpoc.response;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@AllArgsConstructor
//@Setter
//@Getter
public @Data class DataResponse {
	private String message;
	private HttpStatus status;
	private Object body;

	public DataResponse(String message, HttpStatus status, Object body, Date timeStamp) {
		super();
		this.message = message;
		this.status = status;
		this.body = body;
	}

	public DataResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	public DataResponse(String message, HttpStatus status, Object body) {
		super();
		this.message = message;
		this.status = status;
		this.body = body;
	}

	@Override
	public String toString() {
		return "DataResponse [message=" + message + ", status=" + status + ", body=" + body + "]";
	}

}
