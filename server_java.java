import java.util.*;
import java.io.*;
import java.net.*;

public class server_java {

  protected void start() {
    
    ServerSocket s;
    
	
	
	System.out.println("Port:8080");
    
	
	
	
	
	try {
     
      s = new ServerSocket(8080);
    } catch (Exception e) {
      System.out.println("Error: " + e);
      return;
    }

    
	
	
	
	
	System.out.println("Waiting for connection...");
    for (;;) {
      
	  
	  
	  try {
        
        Socket remote = s.accept();
        
        BufferedReader in = new BufferedReader(new InputStreamReader(remote.getInputStream()));
        PrintWriter out = new PrintWriter(remote.getOutputStream());

        System.out.println("Connected");

       
					out.println("HTTP/1.0 200 OK");
					out.println("Content-Type: text/html");
					out.println("Server: Bot");
				   
					out.println("");
        


        String str = "";
        int i = 0;
        String rqFile = "";
        
		
		
		
					while (!(str = in.readLine()).equals("")){
					  
					  
							  if(i == 0){
								String[] stuff = str.split(" ");
							   
								out.println("name of file: "+stuff[1]);
								rqFile = stuff[1];
								
							  }
					  
					  i++;
					
					
					}

        String filename = rqFile.substring(1);
        
		
		
		if(!filename.trim().equals("")){
          
		  
		  System.out.println("filename: " + filename);
          BufferedReader br = new BufferedReader(new FileReader(filename));
          
		  
		  try {
              
			  
			  StringBuilder sb = new StringBuilder();
              String line = br.readLine();

						  while (line != null) {
							  sb.append(line);
							  sb.append(System.lineSeparator());
							  line = br.readLine();
						  }
              
			  String everything = sb.toString();

              out.println(everything);
          
		  } finally {
              br.close();
          } 
        
		
		}else{
          out.println("no stuff");
        }

				out.flush();
				remote.close();
      
	  } catch (Exception e) {
        System.out.println("Error: " + e);
      }
    }
  }
  

  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  public static void main(String args[]) {
    server_java ws = new server_java();
    ws.start();
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}
