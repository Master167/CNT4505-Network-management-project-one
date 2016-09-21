package clientside;

public class ClientSide {
    
    private static int threadCount;

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
            }
        }
        
        return;
    }
    
}
