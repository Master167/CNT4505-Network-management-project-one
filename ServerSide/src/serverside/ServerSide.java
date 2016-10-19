//package serverside;

import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;
import java.lang.management.*;

public class ServerSide {

    /**
     * @param args the command line arguments
     Authors: Kevin Poon, Khue Nguyen 
     Tasks:
           get/send current date and time
           get/send uptime
           get/send memory use
           get/send Netstat
           get/send current users
           get/send host running processes
           quit
     */
    
    public static void main(String[] args) {
        //declarations
        
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        String command;
        
        //Server socket
        try {
           serverSocket = new ServerSocket(9001);
           System.out.println("Socket created.");
        }
        catch (IOException e) {
           System.out.println(e);
        }
        
        try {
            boolean running = true;
            while (running) {
                //open I/O stream
                clientSocket = serverSocket.accept();
                System.out.println("Client Accepted");
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                //listen for client commands
                
                while(!clientSocket.isClosed() && ((command = in.readLine()) != null)) {
                    System.out.println(command);
                    
                    if (command.equals("exit")) {
                       System.out.println("Exiting.");
                       running = false;
                       break;
                    }
                    
                    else {
                       try {
                          Runtime rt = Runtime.getRuntime();
                          Process pr = rt.exec(command);
         
                          BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
         
                          String line = null;
                          while ((line = input.readLine()) != null) {
                             System.out.println(line);
                             out.println(line);
                          }
                          
                          out.close();
                          System.out.println("Done with process");         
                          pr.waitFor();
                       } 
                    
                       catch (Exception e) {
                          System.out.println("Runtime Catch");
                          System.out.println(e.toString());
                       }                  
                    }
                }
            }
        }
        
        catch (IOException e) {
           System.out.println(e);
        }
        
    }//end main 
  
}//end class ServerSide-----------------------------------------------

