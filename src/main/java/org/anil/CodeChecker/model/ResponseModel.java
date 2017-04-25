package org.anil.CodeChecker.model;

import java.util.List;

public class ResponseModel 
{
	private int problemid;
	
	private int score;
	
	private boolean success;
	
	private boolean compilationerror;
	
	
	private String errormessage="";
	
	private List<ResponseTestCaseModel> responsecases;
	
	public ResponseModel(){
		
	}

	
	
	public List<ResponseTestCaseModel> getResponsecases() {
		return responsecases;
	}



	public void setResponsecases(List<ResponseTestCaseModel> responsecases) {
		this.responsecases = responsecases;
	}



	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}

	public int getProblemid() {
		return problemid;
	}

	public void setProblemid(int problemid) {
		this.problemid = problemid;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean isCompilationerror() {
		return compilationerror;
	}

	public void setCompilationerror(boolean compilationerror) {
		this.compilationerror = compilationerror;
	}


	
	
}
