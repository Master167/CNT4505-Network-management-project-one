package clientside;

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
        System.out.printf("getResults has not been created%n");
    }

    private static void runThreads() {
        for (int i = 0; i < threads.length; i++) {
            threads[i].startProcess();
        }
    }

    
}
