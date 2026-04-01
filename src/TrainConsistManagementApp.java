import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

public class TrainConsistManagementApp {

    // ================= UC14: Custom Exception =================
    static class InvalidCapacityException extends Exception {
        public InvalidCapacityException(String message) {
            super(message);
        }
    }

    // ================= UC7/UC14: Bogie Class with Validation =================
    static class Bogie {
        String name;
        int capacity;

        Bogie(String name, int capacity) throws InvalidCapacityException {
            if (capacity <= 0) {
                throw new InvalidCapacityException("Capacity must be greater than zero");
            }
            this.name = name;
            this.capacity = capacity;
        }

        @Override
        public String toString() {
            return name + " (" + capacity + ")";
        }
    }

    // ================= UC12: Goods Bogie Class =================
    static class GoodsBogie {
        String type;   // e.g., Cylindrical, Rectangular
        String cargo;  // e.g., Petroleum, Coal

        GoodsBogie(String type, String cargo) {
            this.type = type;
            this.cargo = cargo;
        }

        @Override
        public String toString() {
            return type + " Bogie carrying " + cargo;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Train Consist Management App ===");

        // ================= UC1–UC6: Passenger Bogies and Capacities =================
        List<String> trainConsist = new ArrayList<>();
        trainConsist.add("Sleeper");
        trainConsist.add("AC Chair");
        trainConsist.add("First Class");
        trainConsist.remove("AC Chair");

        Set<String> bogieIds = new HashSet<>(Arrays.asList("BG101", "BG102", "BG103"));
        LinkedList<String> orderedTrain = new LinkedList<>(Arrays.asList("Engine", "Sleeper", "AC", "Cargo", "Guard"));
        orderedTrain.add(2, "Pantry"); orderedTrain.removeFirst(); orderedTrain.removeLast();

        LinkedHashSet<String> formation = new LinkedHashSet<>(Arrays.asList("Engine", "Sleeper", "Cargo", "Guard"));
        HashMap<String, Integer> bogieCapacity = new HashMap<>();
        bogieCapacity.put("Sleeper", 72);
        bogieCapacity.put("AC Chair", 60);
        bogieCapacity.put("First Class", 24);

        // ================= UC7: Bogies =================
        List<Bogie> bogies = new ArrayList<>();
        try {
            bogies.add(new Bogie("Sleeper", 72));
            bogies.add(new Bogie("AC Chair", 60));
            bogies.add(new Bogie("First Class", 24));
        } catch (InvalidCapacityException e) {
            System.out.println("Error creating bogie: " + e.getMessage());
        }

        bogies.sort(Comparator.comparingInt(b -> b.capacity));

        // ================= UC11: Regex Validation =================
        System.out.println("\n=== UC11: Train ID & Cargo Code Validation ===");
        System.out.print("Enter Train ID (format TRN-1234): ");
        String trainID = sc.nextLine().trim();

        System.out.print("Enter Cargo Code (format PET-AB): ");
        String cargoCode = sc.nextLine().trim();

        String trainIDPattern = "TRN-\\d{4}";
        String cargoCodePattern = "PET-[A-Z]{2}";

        Pattern trainPattern = Pattern.compile(trainIDPattern);
        Pattern cargoPattern = Pattern.compile(cargoCodePattern);

        Matcher trainMatcher = trainPattern.matcher(trainID);
        Matcher cargoMatcher = cargoPattern.matcher(cargoCode);

        if (trainMatcher.matches()) System.out.println("Train ID is valid.");
        else System.out.println("Invalid Train ID. Format should be TRN-1234.");

        if (cargoMatcher.matches()) System.out.println("Cargo Code is valid.");
        else System.out.println("Invalid Cargo Code. Format should be PET-AB.");

        // ================= UC12: Safety Compliance Check =================
        System.out.println("\n=== UC12: Goods Bogie Safety Compliance ===");
        List<GoodsBogie> goodsBogies = new ArrayList<>();
        goodsBogies.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goodsBogies.add(new GoodsBogie("Rectangular", "Coal"));
        goodsBogies.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goodsBogies.add(new GoodsBogie("Open", "Grain"));

        boolean isSafe = goodsBogies.stream()
                .allMatch(b -> !b.type.equalsIgnoreCase("Cylindrical") || b.cargo.equalsIgnoreCase("Petroleum"));

        goodsBogies.forEach(System.out::println);

        if (isSafe) System.out.println("\nTrain is SAFETY COMPLIANT ✅");
        else System.out.println("\nTrain is UNSAFE ⚠️");

        // ================= UC13: Performance Comparison =================
        System.out.println("\n=== UC13: Performance Comparison (Loops vs Streams) ===");
        List<Bogie> largeBogieList = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            try {
                largeBogieList.add(new Bogie("Sleeper", 50 + (i % 100)));
            } catch (InvalidCapacityException e) {
                System.out.println("Invalid capacity in test dataset: " + e.getMessage());
            }
        }

        long startLoop = System.nanoTime();
        List<Bogie> filteredLoop = new ArrayList<>();
        for (Bogie b : largeBogieList) {
            if (b.capacity > 60) filteredLoop.add(b);
        }
        long endLoop = System.nanoTime();
        long loopTime = endLoop - startLoop;

        long startStream = System.nanoTime();
        List<Bogie> filteredStream = largeBogieList.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());
        long endStream = System.nanoTime();
        long streamTime = endStream - startStream;

        System.out.println("Loop filtered bogies count: " + filteredLoop.size());
        System.out.println("Stream filtered bogies count: " + filteredStream.size());
        System.out.println("Loop execution time (ns): " + loopTime);
        System.out.println("Stream execution time (ns): " + streamTime);

        sc.close();
    }
}