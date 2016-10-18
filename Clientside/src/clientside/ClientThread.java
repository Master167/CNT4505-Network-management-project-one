//package clientside;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * This class is made to be the thread that communicates with the server.
 * @author Michael Frederick (initially)
 */
public class ClientThread extends Thread {
    private double elaspedTime;
    private long startTime;
    private String serverCommand;
    private String myHost;
    
    // We'll just use these as constants
    public static final int portNumber = 9001;
    public static final String hostname = "192.168.100.105";
    
    public ClientThread(String command, String myHost) {
        this.serverCommand = command;
        this.elaspedTime = 0;
        this.myHost = myHost;
    }
    
    public double getElaspedTime() {
        return this.elaspedTime;
    }
    
    public String getServerCommand() {
        return this.serverCommand;
    }
    
    public void run() {
        try {
            String str;
            // Do the stuffs
            startTimer();
            Socket socket = new Socket(this.myHost, this.portNumber);
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output.println(this.serverCommand);
	    String line = null;
	    System.out.println("From Server: ");
	    while((line = input.readLine()) != null) {
            	System.out.println(line);
	    }
	    System.out.println("-----------------------------------------------------------------------");
            socket.close();
            endTimer();
        }
        catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        catch (Exception ex) {
            System.out.printf("Exception thrown%n");
        }
        
    }
    
    private void startTimer() {
        this.startTime = System.currentTimeMillis();
    }
    
    private void endTimer() {
        long currentTime = System.currentTimeMillis();
        this.elaspedTime = currentTime - this.startTime;
    }
}
