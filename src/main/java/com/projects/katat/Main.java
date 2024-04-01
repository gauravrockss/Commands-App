package com.projects.katat;


import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;


public class Main {
    private static final String COMMANDS_FILE_PATH = "src/main/java/com/projects/katat/commands/commands.json";
    private static final Stack<String> commandHistory = new Stack<>();
    private static final Stack<String> redoHistory = new Stack<>();
    private static String[] commands;
    public static void main(String[] args) {
        loadCommands();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayMenu();
            String input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
                case "i":
                    issueCommand();
                    break;
                case "l":
                    listCommands();
                    break;
                case "u":
                    undoCommand();
                    break;
                case "r":
                    redoCommand();
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

        private static void issueCommand() {
        Random random = new Random();
        int index = random.nextInt(commands.length);
        String command = commands[index];
        System.out.println("Issuing command: " + command);
        commandHistory.push(command);
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

    private static void undoCommand() {
        if (!commandHistory.isEmpty()) {
            String undoneCommand = commandHistory.pop();
            System.out.println("Undoing command: " + undoneCommand);
            redoHistory.push(undoneCommand);
        } else {
            System.out.println("No command to undo.");
        }
    }

    private static void redoCommand() {
        if (!redoHistory.isEmpty()) {
            String redoneCommand = redoHistory.pop();
            System.out.println("Redoing command: " + redoneCommand);
            commandHistory.push(redoneCommand);
        } else {
            System.out.println("No command to redo.");
        }
    }

    private static void loadCommands() {
        try {
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(COMMANDS_FILE_PATH));
            commands = new String[jsonArray.size()];
            for (int i = 0; i < jsonArray.size(); i++) {
                commands[i] = (String) jsonArray.get(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}