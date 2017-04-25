package org.anil.CodeChecker.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TestCaseModel {

	private int id;
	private String input;
	private String output;
	
	public TestCaseModel(){
		
	}
	
	public TestCaseModel(int id,String input,String output){
		this.id = id;
		this.input = input;
		this.output = output;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	
	
	
}
