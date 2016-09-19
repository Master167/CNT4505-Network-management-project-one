package clientside;

import java.util.Timer;

/**
 * This class is made to be the thread that communicates with the server.
 * @author Michael Frederick (initially)
 */
public class ClientThread {
    private double elaspedTime;
    private long startTime;
    private String serverCommand;
    
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
    
    public void startProcess() {
        // Do the stuffs
    }
    
    private void startTimer() {
        this.startTime = System.currentTimeMillis();
    }
    
    private void endTimer() {
        long currentTime = System.currentTimeMillis();
        this.elaspedTime = currentTime - this.startTime;
    }
}
