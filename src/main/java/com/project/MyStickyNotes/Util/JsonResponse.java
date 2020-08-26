package com.project.MyStickyNotes.Util;


public class JsonResponse {



	String message;
	boolean validation;
	String token;
	String username;
	
	public JsonResponse(String message, boolean validation, String token, String username) {
		super();
		this.message = message;
		this.validation = validation;
		this.token = token;
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public JsonResponse(String message, boolean validation) {
		this.message = message;
		this.validation = validation;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	



	
	public JsonResponse setSuccessData(int rownumber) {
		if(rownumber >=1) {
			this.message = "Data inserted successfully";
			
		}
		else {
			this.message = "Data not inserted";
			
		}
		return this;
	}
	
	public JsonResponse setLoginData(boolean validate) {
		if(validate) {
			this.message = "logged in succcessful";
			this.validation = true;
		}
		else {
			this.message = "username or password is incorrect";
			this.validation = false;
		}
		return this;
	}

	public boolean isValidation() {
		return validation;
	}

	public void setValidation(boolean validation) {
		this.validation = validation;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
