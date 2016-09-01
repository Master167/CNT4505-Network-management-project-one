/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientside;

/**
 *
 * @author Michael
 */
public class UserInterface {
    
    private int threadCount;
    
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
        System.out.printf("Enter Selection [Numbers only]:");
    }
}
