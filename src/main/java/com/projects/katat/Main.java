package com.projects.katat;


import java.util.Scanner;
import java.util.Stack;


public class Main {
    private static final String COMMANDS_FILE_PATH = "src/main/java/com/projects/katat/commands/commands.json";
    private static final Stack<String> commandHistory = new Stack<>();
    private static final Stack<String> redoHistory = new Stack<>();
    private static String[] commands;
    public static void main(String[] args) {
        // loadCommands();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayMenu();
            String input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
                case "i":
                    // issueCommand();
                    break;
                case "l":
                    listCommands();
                    break;
                case "u":
                    // undoCommand();
                    break;
                case "r":
                    // redoCommand();
                    break;
                case "q":
                    running = false;
                    System.out.println("Exiting from the application...");
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\nCavazos Commander App Menu:");
        System.out.println("i - Issue a random command");
        System.out.println("l - List all commands");
        System.out.println("u - Undo last command");
        System.out.println("r - Redo last undone command");
        System.out.println("q - Quit the application");
        System.out.print("Enter your command: ");
    }

    private static void listCommands() {
        System.out.println("\nList of Available Commands:");
        for (int i = 0; i < commands.length; i++) {
            System.out.println((i + 1) + ". " + commands[i]);
        }
    }

}