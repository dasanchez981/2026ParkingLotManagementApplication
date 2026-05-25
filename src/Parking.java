import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Parking {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("Usage: java Parking <filename>");
            return;
        }

        List<String> lines = Files.readAllLines(Path.of(args[0]));

        int count = lines.size();

        System.out.println("Number of lines: " + count);
    }
}
