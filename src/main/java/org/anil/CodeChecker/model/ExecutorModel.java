package org.anil.CodeChecker.model;

public class ExecutorModel {


    private boolean success;
    private boolean timelimit;
    private boolean wronganswer;

    private boolean runtimeerror;
    private String error;


    public ExecutorModel(){

    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isTimelimit() {
        return timelimit;
    }

    public void setWronganswer(boolean wronganswer) {
        this.wronganswer = wronganswer;
    }

    public boolean isWronganswer() {
        return wronganswer;
    }

    public void setTimelimit(boolean timelimit) {
        this.timelimit = timelimit;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isRuntimeerror() {
        return runtimeerror;
    }

    public void setRuntimeerror(boolean runtimeerror) {
        this.runtimeerror = runtimeerror;
    }


}
