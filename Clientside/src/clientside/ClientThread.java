package clientside;

import java.io.IOException;
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
    
    // We'll just use these as constants
    private static final int portNumber = 9001;
    private static final String hostname = "";
    
    public ClientThread(String command) {
        this.serverCommand = command;
        this.elaspedTime = 0;
    }
    
    public double getElaspedTime() {
        return this.elaspedTime;
    }
    
    public String getServerCommand() {
        return this.serverCommand;
    }
    
    public void run() {
        try {
            // Do the stuffs
            //Socket socket = new Socket(this.hostname, this.portNumber);
            startTimer();
            Thread.sleep((long) (Math.random() * 100.0));
            endTimer();
        }
        catch (Exception ex) {
            System.out.printf("Exception thrown%n");
        }
//        catch (UnknownHostException ex) {
//            ex.printStackTrace();
//        }
//        catch (IOException ex) {
//            ex.printStackTrace();
//        }
        
    }
    
    private void startTimer() {
        this.startTime = System.currentTimeMillis();
    }
    
    private void endTimer() {
        long currentTime = System.currentTimeMillis();
        this.elaspedTime = currentTime - this.startTime;
    }
}
