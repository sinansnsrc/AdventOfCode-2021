import java.io.IOException;

public class AoC1 extends AdventOfCodeDay {
    public AoC1(String filepath) throws IOException {
        super(filepath);
    }

    public long partASolution() {
        int count = 0;
        for(int i = 1; i < values.size(); i++) {
            if(Integer.parseInt(values.get(i)) > Integer.parseInt(values.get(i - 1))) {
                count++;
            }
        }
        return count;
    }

    public long partBSolution() {
        int count = 0;
        for(int i = 3; i < values.size(); i++) {
            int previousWindow = Integer.parseInt(values.get(i - 3)) + Integer.parseInt(values.get(i - 2)) + Integer.parseInt(values.get(i - 1));
            int currentWindow = Integer.parseInt(values.get(i - 2)) + Integer.parseInt(values.get(i - 1)) + Integer.parseInt(values.get(i));
            if(currentWindow > previousWindow) {
                count++;
            }
        }
        return count;
    }
}
