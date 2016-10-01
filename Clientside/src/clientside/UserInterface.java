package clientside;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Michael
 */
public class UserInterface {
    
    private int threadCount;
    private String[] serverCommands = {"thread" ,"date", "uptime", "free", "netstat", 
        "users", "pgrep", "exit"};
    
    public UserInterface() {
        this.threadCount = 0;
    }
    
    public UserInterface(int threadCount) {
        this.threadCount = threadCount;
    }
    
    public void displayMenu() {
        System.out.printf("Select a Command to be run on the server%n");
        System.out.printf("Threads to be used %d%n", this.threadCount);
        System.out.printf("0. Change Thread Count%n");
        System.out.printf("1. Get Server Date and Time%n");
        System.out.printf("2. Get Server Uptime%n");
        System.out.printf("3. Get Server memory%n");
        System.out.printf("4. Get Server Netstat%n");
        System.out.printf("5. Get Server Current Users%n");
        System.out.printf("6. Get Server running processes%n");
        System.out.printf("7. Exit Program%n");
        System.out.printf("Enter Selection [Numbers only]: ");
        return;
    }
    
    public int getUserInput() {
        int number;
        Scanner input = new Scanner(System.in);
        
        try {
            number = input.nextInt();
            if (number < 0 || number > 7) {
                System.out.printf("Invalid Command Given%n");
                number = getUserInput();
            }
        }
        catch (InputMismatchException ex) {
            System.out.printf("Invalid Input: Input an Integer only%n");
            number = getUserInput();
        }
        
        return number;
    }
    
    public String getCommand() {
        int commandId = this.getUserInput();
        
        return this.serverCommands[commandId];
    }
    
    public int changeThreadCount() {
        int newCount = 1;
        
        System.out.printf("Enter new thread Count: ");
        Scanner input = new Scanner(System.in);
        try {
            newCount = input.nextInt();
        }
        catch (InputMismatchException ex) {
            System.out.printf("Invalid Input: Input an Integer only%n");
            newCount = this.changeThreadCount();
        }

        this.threadCount = newCount;
        return newCount;
    }
}
