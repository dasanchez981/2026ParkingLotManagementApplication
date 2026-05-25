import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Parking {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("Usage: java Parking <input-file>");
            return;
        }

        List<String> lines = Files.readAllLines(Path.of(args[0]));

        if (lines.isEmpty()) {
            System.out.println("Input file is empty.");
            return;
        }

        int n = Integer.parseInt(lines.get(0).trim());

        String[] smallSpots = new String[n];
        String[] largeSpots = new String[n];
        String[] oversizedSpots = new String[n];

        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i).trim();

            if (line.equals("Exit")) {
                break;
            }

            String[] parts = line.split("\\s+");
            String command = parts[0];

            if (command.equals("Enter")) {
                String size = normalizeSize(parts[1]);
                String plate = parts[2];

                String[] spots = getSpots(size, smallSpots, largeSpots, oversizedSpots);
                handleEnter(size, plate, spots);
            } else if (command.equals("Leave")) {
                String size = normalizeSize(parts[1]);
                String plate = parts[2];

                String[] spots = getSpots(size, smallSpots, largeSpots, oversizedSpots);
                handleLeave(size, plate, spots);
            } else if (command.equals("Status")) {
                printStatus(smallSpots, largeSpots, oversizedSpots);
            }
        }
    }

    private static String normalizeSize(String size) {
        size = size.toLowerCase();

        if (size.equals("small")) {
            return "small";
        } else if (size.equals("large")) {
            return "large";
        } else if (size.equals("oversize") || size.equals("oversized")) {
            return "oversized";
        }

        throw new IllegalArgumentException("Unknown vehicle size: " + size);
    }

    private static String[] getSpots(
            String size,
            String[] smallSpots,
            String[] largeSpots,
            String[] oversizedSpots
    ) {
        if (size.equals("small")) {
            return smallSpots;
        } else if (size.equals("large")) {
            return largeSpots;
        } else if (size.equals("oversized")) {
            return oversizedSpots;
        }

        throw new IllegalArgumentException("Unknown vehicle size: " + size);
    }

    private static void handleEnter(String size, String plate, String[] spots) {
        for (int i = 0; i < spots.length; i++) {
            if (spots[i] == null) {
                spots[i] = plate;
                System.out.println(size + " vehicle with license plate " + plate
                        + " is parking in spot " + (i + 1) + ".");
                return;
            }
        }

        System.out.println("No more " + size + " spots available for vehicle with id " + plate + ".");
    }

    private static void handleLeave(String size, String plate, String[] spots) {
        for (int i = 0; i < spots.length; i++) {
            if (plate.equals(spots[i])) {
                spots[i] = null;
                System.out.println(size + " vehicle with license plate " + plate
                        + " is leaving spot " + (i + 1) + ".");
                return;
            }
        }

        System.out.println(size + " vehicle with license plate " + plate + " is not currently parked.");
    }

    private static void printStatus(String[] smallSpots, String[] largeSpots, String[] oversizedSpots) {
        System.out.println("Status:");

        System.out.println("Small spots:");
        printSpots(smallSpots);

        System.out.println("Large spots:");
        printSpots(largeSpots);

        System.out.println("Oversized spots:");
        printSpots(oversizedSpots);
    }

    private static void printSpots(String[] spots) {
        for (int i = 0; i < spots.length; i++) {
            if (spots[i] == null) {
                System.out.println((i + 1) + " Empty");
            } else {
                System.out.println((i + 1) + " " + spots[i]);
            }
        }
    }
}
