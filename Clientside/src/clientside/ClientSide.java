package clientside;

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
        boolean running = true;
        
        if (args.length > 0 && !args[0].equals("")) {
            threadCount = Integer.parseInt(args[0]);
        }
        else {
            threadCount = 1;
        }
        
        if (threadCount < 1) {
            threadCount = 1;
        }
        
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
                generateThreads(threadCount, command);
                runThreads();
                getResults();
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
    private static void generateThreads(int threadCount, String command) {
        threads = new ClientThread[threadCount];
        
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new ClientThread(command);
        }
    }

    private static void getResults() {
        System.out.printf("Results from threads%n");
        for(ClientThread t : threads) {
            System.out.printf("%.2f, ", t.getElaspedTime());
        }
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
