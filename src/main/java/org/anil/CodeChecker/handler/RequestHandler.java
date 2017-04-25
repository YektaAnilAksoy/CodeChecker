package org.anil.CodeChecker.handler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.anil.CodeChecker.model.RequestModel;
import org.anil.CodeChecker.model.ResponseModel;
import org.anil.CodeChecker.model.TestCaseModel;
import org.anil.CodeChecker.process.CheckerProcess;
import org.anil.CodeChecker.writer.FileCreator;


@Path("/handler")
public class RequestHandler {

	/* PATH OF USERS  */
   

    /*
    *   YAPILACAKLAR
    * EGER THREADPOOL DOLARSA GELEN ISTEKLERI QUEUE DA BEKLETECEGIM.
    * GELEN JSON PARCALANIP ICINDEN KOD ALINIP DOSYAYA YAZILACAK
    * WorkerThreade  USERPATH + username + soru idsi ve language  gönderilecek
    *
    * PROCESS KİLL OLUYOR MU OLMUYOR MU ONU CHECK ET
    * TESTCASELERİ AYARLA
    * */

    private static final String  USERPATH = "/home/server/USERS/";
    
    private ExecutorService executor = Executors.newFixedThreadPool(300);
   

    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RequestModel getModel(){

	
		RequestModel model = new RequestModel();
	   	 String code = "import java.util.*;\n" +
	             "\n" +
	             "public class Solution {\n" +
	             "\n" +
	             "    public static void main(String[] args){\n" +
	             "\tScanner sc = new Scanner(System.in);\n" +
	             "\tString input = sc.nextLine();\n" +
	             "\twhile(true){}\n" +
	             "    }\n" +
	             "}";
		model.setSubmittedcode(code);
		model.setScore(50);
		List<TestCaseModel> testlist = new ArrayList<TestCaseModel>();
		testlist.add(new TestCaseModel(0,"1","2"));
		testlist.add(new TestCaseModel(1,"3","4"));
		testlist.add(new TestCaseModel(2,"5","6"));
		model.setProblemid("1");
		model.setTestcases(testlist);
		model.setUsername("anil");
		model.setTimelimit(4000);
		model.setLang("java");
		return model;
    }
    
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel getResponse(RequestModel request){
		
		
		String classpath = USERPATH + request.getUsername() + "/" +request.getProblemid()+"/";
		
        File file = new File(classpath+"Solution.java");
        
        try {
        	//eger classpathde kullanilan klasorler olusturulmamissa onları olusturuyor
            file.getParentFile().mkdirs();
            //file yaratiyor
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
        ResponseModel response = new ResponseModel();
        
        if(file.exists()){
        	
            System.out.println("File CREATED...");
            FileCreator.WriteToFile(file,request.getSubmittedcode());
            response.setScore(request.getScore());
            Runnable worker = new CheckerProcess(request.getLang(),classpath,request.getTestcases(),request.getTimelimit(),response);
            Future future = executor.submit(worker);

            try {
                if(future.get()==null){
                    System.out.println("CIKTII"+((ThreadPoolExecutor) executor).getActiveCount());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

           
            try {
               Object object = future.get(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            } finally {
                future.cancel(true);
            }
        }
        return response;

	}
    
    
    
    
}
