import java.util.ArrayList;
import java.util.List;

public class TrainConsistManagementApp {

    public static void main(String[] args) {

        // Welcome Message
        System.out.println("=== Train Consist Management App ===");

        // UC1: Initialize Train Consist (Empty List)
        List<String> trainConsist = new ArrayList<>();

        // Display Initial Bogie Count
        System.out.println("Train consist initialized.");
        System.out.println("Initial number of bogies: " + trainConsist.size());

        // ================= UC2 START =================

        // Add Passenger Bogies
        trainConsist.add("Sleeper");
        trainConsist.add("AC Chair");
        trainConsist.add("First Class");

        System.out.println("\nPassenger bogies added:");
        System.out.println(trainConsist);

        // Remove a bogie
        trainConsist.remove("AC Chair");
        System.out.println("\nAfter removing AC Chair:");
        System.out.println(trainConsist);

        // Check existence
        if (trainConsist.contains("Sleeper")) {
            System.out.println("\nSleeper bogie exists in the train.");
        } else {
            System.out.println("\nSleeper bogie not found.");
        }

        // Final state
        System.out.println("\nFinal Train Consist:");
        System.out.println(trainConsist);

        // ================= UC2 END =================
    }
}