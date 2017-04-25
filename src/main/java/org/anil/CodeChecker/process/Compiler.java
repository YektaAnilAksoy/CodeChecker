package org.anil.CodeChecker.process;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Compiler {
	private ProcessBuilder p;

    private String lang;
    private String path;
    private boolean compiled;

    public Compiler(String lang,String path){
        this.lang = lang;
        this.path = path;
        compiled = false;
    }


    public String Compile(){
        String result="success";

        System.out.println("Code compilation started...");


        if(lang.equals("java")){
            System.out.println("java code is being compiled now ...");

            p = new ProcessBuilder("javac","Solution.java");
        }
        else{
            System.out.println("YANLIS LANGUAGE SECILMIS");
            p = new ProcessBuilder();
        }

        //process in calisacagi directoryi
        p.directory(new File(path));
        System.out.println("PATH = "+path);
        //Kodda hata varsa print etmemizi sagliyor, bu kapalı olursa erroru alamayiz.
        p.redirectErrorStream(true);

        try{
            Process pp = p.start();
            InputStream is = pp.getInputStream();
            String temp;
            String error="";
            try(BufferedReader b = new BufferedReader(new InputStreamReader(is))){

                // error degiskenine error ciktisi ekleniyor ve response modeline ekleniyor.
                while((temp = b.readLine()) != null){
                    compiled = false;
                    System.out.println(temp);
                    error += temp;
                    result = error;
                }



                //usteki kisim isini bitirene kadar blockluyor processi
                pp.waitFor();
            }

            if(!compiled){
                is.close();
                System.out.println("COMPILATION ERROR OCCURED");
                return result;
            }
            is.close();
            System.out.println("COMPILATION SUCCESS");


        }catch (IOException | InterruptedException e){
            System.out.println("in compile() "+e);
            result = e.getMessage();
        }


        return result;
    }
}
