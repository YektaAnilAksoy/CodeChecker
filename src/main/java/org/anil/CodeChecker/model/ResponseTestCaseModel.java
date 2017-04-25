package org.anil.CodeChecker.model;

public class ResponseTestCaseModel {
	private int id;
	private boolean succes;
	private String error;
	
	public ResponseTestCaseModel(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isSucces() {
		return succes;
	}

	public void setSucces(boolean succes) {
		this.succes = succes;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	
}
