import java.io.IOException;

public class AoC2 extends AdventOfCodeDay{

    public AoC2(String filepath) throws IOException {
        super(filepath);
    }

    public long partASolution() {
        int[] position = new int[] {0,0};

        for(String i : values) {
            String command = i.substring(0, i.indexOf(" "));
            int value = Integer.parseInt(i.substring(i.indexOf(" ") + 1));
            if(command.equals("up")) {
                position[0] -= value;
            }
            else if(command.equals("down")) {
                position[0] += value;
            }
            else {
                position[1] += value;
            }
        }

        return position[0] * position[1];
    }

    public long partBSolution() {
        int aim = 0;
        int[] position = new int[] {0,0};

        for(String i : values) {
            String command = i.substring(0, i.indexOf(" "));
            int value = Integer.parseInt(i.substring(i.indexOf(" ") + 1));
            if(command.equals("up")) {
                aim -= value;
            }
            else if(command.equals("down")) {
                aim += value;
            }
            else {
                position[0] += aim * value;
                position[1] += value;
            }
        }

        return position[0] * position[1];
    }
}
