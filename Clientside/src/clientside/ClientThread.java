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
    private double totalTime;
    private long startTime;
    private String serverCommand;
    private String myHost;
    
    // We'll just use these as constants
    public static final int portNumber = 9001;
    
    public ClientThread(String command, String myHost) {
        this.serverCommand = command;
        this.elaspedTime = 0;
        this.myHost = myHost;
    }
    
    public double getElaspedTime() {
        return this.elaspedTime;
    }
    
    public double getTotalTime() {
	return this.totalTime;
    }
	
    public String getServerCommand() {
        return this.serverCommand;
    }
    
    public void run() {
        try {
            String str;
	    String line = null;
            // Do the stuffs
            Socket socket = new Socket(this.myHost, this.portNumber);
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    
	    totalTime = 0;
	    startTimer(); //moved the timers closer to the request
            output.println(this.serverCommand);
	    
	    while((line = input.readLine()) != null) {
		endTimer();
		this.totalTime += this.elaspedTime;
            	System.out.println(line);
		startTimer();
	    }
	    endTimer();
	    this.totalTime += this.elaspedTime;
	    System.out.println("----------------------------------------------------------------------");
            socket.close();
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
