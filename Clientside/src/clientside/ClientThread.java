package clientside;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
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
    private static final String hostname = "192.168.100.106";
    
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
            startTimer();
            Socket socket = new Socket(this.hostname, this.portNumber);
            PrintStream outputStream = new PrintStream(socket.getOutputStream());
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            outputStream.printf(this.serverCommand);
            System.out.printf(inputStream.readLine());
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
