package org.anil.CodeChecker.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileCreator {

    public static void WriteToFile(File file, String code){
        //file.delete();
        try{
            BufferedWriter out = new BufferedWriter(new FileWriter(file), 32768);
            out.write(code);
            out.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
