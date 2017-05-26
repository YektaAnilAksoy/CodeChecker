package org.anil.CodeChecker.process;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.concurrent.TimeUnit;

import org.anil.CodeChecker.model.ExecutorModel;

public class Executor {
    private ProcessBuilder p;
    private String path;
    private String input;
    private String output;
    private String lang;
    private long timeInMillis;

    public Executor(String path,String input, String output,String lang,long timeInMillis ){
        this.path = path;
        this.input = input;
        this.output = output;
        this.lang = lang;
        this.timeInMillis = timeInMillis;

    }


    public ExecutorModel execute(){
        ExecutorModel model = new ExecutorModel();

        System.out.println("Code started executing");

        if(lang.equals("java")){
            p = new ProcessBuilder("java","Solution");
        }
        else if(lang.equals("c")){
            p = new ProcessBuilder("./a.out");
        }
        else if(lang.equals("c++")){
            p = new ProcessBuilder("./a.out");
        }
        else{
            System.out.println("language is not correct...");
            p = null;
        }

        p.directory(new File(path));

        p.redirectErrorStream(true);

       // System.out.println("Current directory "+System.getProperty("user.dir"));

        try{
            Process pp = p.start();

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(pp.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line = null;



            /*process e input veriliyor bu kısımda */
            OutputStream outputstream = pp.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputstream));
            writer.write(input);
            
            writer.flush();
            writer.close();

            if(!pp.waitFor(timeInMillis, TimeUnit.MILLISECONDS)){
                System.out.println("TİME LİMİT EXCEED !!!! ");
                model.setTimelimit(true);       
                return model;
            }

            else{

                model.setTimelimit(false);
                int exitCode = pp.exitValue();
                System.out.println("Exit Value = "+pp.exitValue());

                if(exitCode != 0){
                    System.out.println("RUNTIME ERROR !!!!!!");

                    model.setSuccess(false);
                    model.setRuntimeerror(true);
                    return model;
                }
            }

            

            while ( (line = reader.readLine()) != null) {
                builder.append(line);
                //builder.append(System.getProperty("line.separator"));
            }
            String result = builder.toString();
            System.out.println(" output:"+result+" input:"+input);
            if(result.charAt(result.length()-1) == ' ')
            	result = result.substring(0, result.length()-1);
            if(result.equals(output)){
                model.setSuccess(true);
                model.setWronganswer(false);
                System.out.println("OUTPUT (SUCCESS) = "+result);
                return model;
            }
            else{
                model.setSuccess(false);
                model.setWronganswer(true);
                System.out.println("OUTPUTTT (FAIL) = "+result);
                return model;
            }



        }catch(IOException ioe){
            System.err.println("in execute() "+ioe);
        }catch (InterruptedException ex){
            System.err.println(ex);
        }



        System.out.println("CODE EXECUTION FINISHED !");


        return model;
    }



}
