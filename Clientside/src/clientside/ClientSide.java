//package clientside;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


public class ClientSide {
    
    private static int threadCount;
    private static ClientThread[] threads;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UserInterface ui;
        String command;
        String myHost;
        boolean running = true;
        
        if (args.length < 1) { //ends if no command line arg.
            System.out.println("Error: No host name. Exiting.");
            return;
        }    
        else {
            myHost = args[0];
            if (args.length == 2) { //optional command ln arg for thread count
                threadCount = Integer.parseInt(args[1]);
            }
            else {
                System.out.println("Default thread count: 1");
                threadCount = 1;
            }
        }
        if (threadCount < 1) {
            threadCount = 1;
        }
        /*    
        if (args.length > 0 && !args[0].equals("")) {
            //threadCount = Integer.parseInt(args[0]);
        }
        
        else {
            threadCount = 1;
        }
        */
        ui = new UserInterface(threadCount);
        while (running) {
            ui.displayMenu();
            command = ui.getCommand();

            if (command.equals("thread")) {
                threadCount = ui.changeThreadCount();
            }
            else if (command.equals("exit")) {
                signalServerExit();
                System.out.printf("Ending program%n");
                running = false;
            }
            else {
                System.out.printf("Command is: %s%n", command);
                System.out.println("From Server: ");
                generateThreads(threadCount, command, myHost);
                runThreads();
                getResults(threadCount);
            }
        }
        
        return;
    }
    
    /**
     * Builds the threads for the specified command, threads will be stored in
     * threads[]
     * @param threadCount
     * @param command 
     */
    private static void generateThreads(int threadCount, String command, String myHost) {
        threads = new ClientThread[threadCount];
        
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new ClientThread(command, myHost);
        }
    }

    private static void getResults(int threadCount) {
        double sum = 0;
        
        System.out.printf("Server response times (milliseconds): %n");
        for(ClientThread t : threads) {
            System.out.printf("%.2f, ", t.getElaspedTime());
	    sum += t.getElaspedTime();
        }
        System.out.printf("%nLatency (mean server response time): %.2f ms", (sum/((double)threadCount)));
        System.out.printf("%n");
    }

    private static void runThreads() {
        int i;
        boolean running = true;
        for (i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        while (running) {
            running = false;
            for (i = 0; i < threads.length; i++) {
                if (threads[i].isAlive()) {
                    running = true;
                }
            }// end for
        }//end while
    }
    
    private static void signalServerExit() {
        
        try {
            // Signal Server End
            Socket socket = new Socket(ClientThread.hostname, ClientThread.portNumber);
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            output.printf("exit%n");
            socket.close();
        }
        catch (IOException ex) {}
    }

    
}
