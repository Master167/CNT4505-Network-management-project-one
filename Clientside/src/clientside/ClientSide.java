package clientside;

public class ClientSide {
    
    private static int threadCount;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UserInterface ui;
        int command;
        
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
        ui.displayMenu();
        command = ui.getUserInput();
        
        return;
    }
    
}
