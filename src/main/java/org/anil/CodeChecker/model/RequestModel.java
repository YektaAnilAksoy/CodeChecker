package org.anil.CodeChecker.model;


import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RequestModel {

	private String username;
	private String submittedcode;
	private int score;
	private List<TestCaseModel> testcases;
	private String lang;
	private String Problemid;
	private long timelimit;
	
	public RequestModel(){
	}

	
	
	public String getLang() {
		return lang;
	}



	public void setLang(String lang) {
		this.lang = lang;
	}

	

	public long getTimelimit() {
		return timelimit;
	}



	public void setTimelimit(long timelimit) {
		this.timelimit = timelimit;
	}



	public String getProblemid() {
		return Problemid;
	}



	public void setProblemid(String problemid) {
		Problemid = problemid;
	}



	public List<TestCaseModel> getTestcases() {
		return testcases;
	}

	public void setTestcases(List<TestCaseModel> testcases) {
		this.testcases = testcases;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSubmittedcode() {
		return submittedcode;
	}

	public void setSubmittedcode(String submittedcode) {
		this.submittedcode = submittedcode;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
}
