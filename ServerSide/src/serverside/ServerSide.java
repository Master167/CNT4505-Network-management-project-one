package serverside;

import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;
import java.lang.management.*;

public class ServerSide {

    /**
     * @param args the command line arguments
     Authors: Kevin Poon, 
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
        ServerSocket echoServer = null;
        String line;
        DataInputStream is;
        PrintStream os;
        Socket clientSocket = null;
        
        //Server socket
        try {
           echoServer = new ServerSocket(9001);
           System.out.println("Socket connection created.");
        }
        catch (IOException e) {
           System.out.println(e);
        }
        
        try { //open I/O stream
           clientSocket = echoServer.accept();
           is = new DataInputStream(clientSocket.getInputStream());
           os = new PrintStream(clientSocket.getOutputStream());
           while (true) { //echo data back to client
             line = is.readLine();
             os.println(line); 
           }//end while
        }   
        catch (IOException e) {
           System.out.println(e);
        }
        
    }//end main 
    
    public void getDate() {
        Date date = new Date();
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        System.out.println(dateFormat.format(date));
        
        return;  
    }//end getDate()

    public void getUptime() {
        RuntimeMXBean rb = ManagementFactory.getRuntimeMXBean();
        long uptime = rb.getUptime();
        System.out.println(uptime);
        return;
    }//end getUptime
    
    /*
    memory use method here
    */
    
    
}//end class ServerSide-----------------------------------------------

//useful code

/*
        //open socket
        ServerSocket MyService; 
        try {
            MyServerice = new ServerSocket(PortNumber);
        }
        catch (IOException e) {
            System.out.println(e);
        }
        
        //create server socket for listening
        Socket clientSocket = null;
        try {
            serviceSocket = MyService.accept();
        }
        catch (IOException e) {
            System.out.println(e);
        }
        
        //recieve input stream from Client
        DataInputStream input;
        try {
            input = new DataInputStream(serviceSocket.getInputStream());
        }
        catch (IOException e) {
        System.out.println(e);
        }
        
        //send output stream to Client
        PrintStream output;
        try {
            output = new PrintStream(serviceSocket.getOutputStream());
        }
        catch (IOException e) {
            System.out.println(e);
        }
*/

