package org.anil.CodeChecker.process;

import java.util.ArrayList;
import java.util.List;

import org.anil.CodeChecker.model.ExecutorModel;
import org.anil.CodeChecker.model.ResponseModel;
import org.anil.CodeChecker.model.ResponseTestCaseModel;
import org.anil.CodeChecker.model.TestCaseModel;

public class CheckerProcess implements Runnable {

    private String path;
    private String input;
    private String lang;
    private String output;
        
    private long timeInMillis;
   
    private ResponseModel response;
    
    private Compiler compiler;
    
    private int numberofsuccess=0;
    
    private final double testcasescore;
    
    private List<TestCaseModel> testcases;
    
    public CheckerProcess(String lang,String path,List<TestCaseModel> testcases,long timeInMillis,ResponseModel response){
        this.lang = lang;
    	this.path = path;
    	this.testcases = testcases;
        this.timeInMillis = timeInMillis;
        this.response = response;
        testcasescore = (double)response.getScore()/testcases.size();
        compiler = new Compiler(lang,path);
        
        
    }

    @Override
    public void run() {

        String compilationresult = compiler.Compile();
        
        if(compilationresult.equals("success")){
            /*EXECUTION PART*/
        	
        	response.setCompilationerror(false);
        	response.setSuccess(true);
        	
        	Executor executor;
        	ExecutorModel executormodel;
        	List<ResponseTestCaseModel> responsecases = new ArrayList<ResponseTestCaseModel>();
        	
        	for(int i = 0;i<testcases.size();i++){
        		input = testcases.get(i).getInput();
        		output = testcases.get(i).getOutput();
            	
        		executor = new Executor(path,input,output,lang
                        ,timeInMillis);
                        
                executormodel = executor.execute();
                
                ResponseTestCaseModel responsecase = new ResponseTestCaseModel();
                responsecase.setId(testcases.get(i).getId());
                responsecase.setError(executormodel.getError());
                responsecase.setSucces(executormodel.isSuccess());
                
                if(executormodel.isRuntimeerror()){
                	responsecase.setError("Runtime Error");
                	response.setErrormessage(executormodel.getError());
                	response.setSuccess(false);
                	responsecases.add(responsecase);
                	response.setResponsecases(responsecases);
                	break;
                }
                else if(executormodel.isTimelimit()){
                	responsecase.setError("Time Limit Exceed");
                	response.setSuccess(false);
                }
                else if(executormodel.isWronganswer()){
                	responsecase.setError("Wrong Answer");
                	response.setSuccess(false);
                }
                else{
                	numberofsuccess++;
                }
            	responsecases.add(responsecase);
            	response.setResponsecases(responsecases);
                        
        	}
        	//Sets the score which user gets.
        	
        	response.setScore((int)(numberofsuccess*testcasescore));
        	System.out.println("submitted score = "+response.getScore());
        }
        else{
            response.setCompilationerror(true);
            response.setErrormessage(compilationresult);
            System.out.println("Compilation error = "+compilationresult);
        }

        System.out.println(Thread.currentThread().getName()+" (End)");



    }
}
