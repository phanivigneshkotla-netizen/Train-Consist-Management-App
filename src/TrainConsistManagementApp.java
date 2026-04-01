import java.util.*;

public class TrainConsistManagementApp {

    public static void main(String[] args) {

        System.out.println("=== Train Consist Management App ===");

        // ================= UC1 =================
        List<String> trainConsist = new ArrayList<>();
        System.out.println("Train consist initialized.");
        System.out.println("Initial number of bogies: " + trainConsist.size());

        // ================= UC2 =================
        trainConsist.add("Sleeper");
        trainConsist.add("AC Chair");
        trainConsist.add("First Class");

        System.out.println("\nPassenger bogies added:");
        System.out.println(trainConsist);

        trainConsist.remove("AC Chair");
        System.out.println("\nAfter removing AC Chair:");
        System.out.println(trainConsist);

        if (trainConsist.contains("Sleeper")) {
            System.out.println("\nSleeper bogie exists in the train.");
        }

        System.out.println("\nFinal Train Consist:");
        System.out.println(trainConsist);

        // ================= UC3 =================
        Set<String> bogieIds = new HashSet<>();

        bogieIds.add("BG101");
        bogieIds.add("BG102");
        bogieIds.add("BG103");
        bogieIds.add("BG101"); // duplicate
        bogieIds.add("BG102"); // duplicate

        System.out.println("\nUnique Bogie IDs:");
        System.out.println(bogieIds);

        // ================= UC4 =================
        LinkedList<String> orderedTrain = new LinkedList<>();

        // Add bogies
        orderedTrain.add("Engine");
        orderedTrain.add("Sleeper");
        orderedTrain.add("AC");
        orderedTrain.add("Cargo");
        orderedTrain.add("Guard");

        System.out.println("\nInitial Train Order:");
        System.out.println(orderedTrain);

        // Insert Pantry Car at position 2
        orderedTrain.add(2, "Pantry");

        System.out.println("\nAfter adding Pantry at position 2:");
        System.out.println(orderedTrain);

        // Remove first and last bogie
        orderedTrain.removeFirst();
        orderedTrain.removeLast();

        System.out.println("\nFinal Ordered Train Consist:");
        System.out.println(orderedTrain);
    }
}