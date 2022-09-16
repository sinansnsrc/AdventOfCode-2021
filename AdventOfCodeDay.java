import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public abstract class AdventOfCodeDay {
    public List<String> values;
    public abstract long partASolution();
    public abstract long partBSolution();

    public AdventOfCodeDay(String filepath) throws IOException {
        values = Files.readAllLines(Paths.get(filepath));
    }

    public void getSolutions() {
        System.out.println("The answer to part A is: " + partASolution());
        System.out.println("The answer to part B is: " + partBSolution());
    }
}
