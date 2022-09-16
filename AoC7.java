import java.io.IOException;
import java.util.*;

public class AoC7 extends AdventOfCodeDay {
    public AoC7(String filepath) throws IOException {
        super(filepath);
    }

    public long partASolution() {
        int[] positions = new int[values.get(0).split(",").length];

        for(int i = 0; i < positions.length; i++) {
            positions[i] = Integer.parseInt(values.get(0).split(",")[i]);
        }

        Arrays.sort(positions);
        double median = (int) ((((double)positions[positions.length/2] + (double)positions[positions.length/2 - 1])/2) + 0.5);

        int sum = 0;

        for(int i = 0; i < positions.length; i++) {
            sum += Math.abs(median - positions[i]);
        }

        return sum;
    }

    public long partBSolution() {
        int[] positions = new int[values.get(0).split(",").length];
        int total = 0;

        for(int i = 0; i < positions.length; i++) {
            positions[i] = Integer.parseInt(values.get(0).split(",")[i]);
            total += positions[i];
        }

        int mean = (int) (((double) total / positions.length));

        int sum = 0;

        for(int i = 0; i < positions.length; i++) {
            int n = Math.abs(mean - positions[i]);
            sum += (n + 1) * n * 0.5;
        }

        return sum;
    }
}
