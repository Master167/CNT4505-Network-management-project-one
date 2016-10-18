package serverside;

import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;
import java.lang.management.*;

public class ServerSide {

    private static final int portNumber = 9001;
    private static final String hostname = "192.168.100.105";
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
                
                while(((command = in.readLine()) != null) && !clientSocket.isClosed()) {
                    /*//Client command: date
                    if (inputLine.equals("date")) {
                        out.println(getDate());
                    }
                    //Client Command: uptime
                    if (inputLine.equals("uptime")) {
                        out.println(getUptime());
                    }
                    //Client command: current users
                    if (inputLine.equals("users")) {
                        out.println(System.getProperty("user.name"));
                    }
                    //Client command: exit
                    if (inputLine.equals("exit")) {
                        System.out.println("Exiting.");
                        running = false;
                        break;
                    } Nope */
                    
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
                             out.println(line);
                          }
         
                          pr.waitFor();
                       } 
                    
                       catch (Exception e) {
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
    
    /*public static String getDate() {
        Date date = new Date();
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        System.out.println(dateFormat.format(date));
        
        return dateFormat.format(date);  
    }//end getDate()
    public static long getUptime() {
        RuntimeMXBean rb = ManagementFactory.getRuntimeMXBean();
        long uptime = rb.getUptime();
        System.out.println(uptime);
        return uptime;
    }//end getUptime
    
    /*
    memory use method here
    Also nope */
    
    
}//end class ServerSide-----------------------------------------------

