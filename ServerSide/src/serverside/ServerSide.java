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
        
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        
        
        //Server socket
        try {
           serverSocket = new ServerSocket(9001);
           System.out.println("Socket connection created.");
        }
        catch (IOException e) {
           System.out.println(e);
        }
        
        try {
            boolean running = true;
            while (running) {
                //open I/O stream
                clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                //listen for client commands
                String inputLine, outputLine;
                while(((inputLine = in.readLine()) != null) && !clientSocket.isClosed()) {
                    //Client command: date
                    if (inputLine.equals("date")) {
                        outputLine = getDate();
                        out.println(outputLine);
                    }
                    //Client Command: uptime
                    if (inputLine.equals("uptime")) {
                        out.println(getUptime());
                    }
                    //Client command: exit
                    if (inputLine.equals("exit")) {
                        System.out.println("Exiting.");
                        running = false;
                        break;
                    }
                }
            }
        }
        catch (IOException e) {
           System.out.println(e);
        }
        
    }//end main 
    
    public static String getDate() {
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

