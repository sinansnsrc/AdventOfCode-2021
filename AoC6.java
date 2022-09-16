import java.io.IOException;
import java.util.ArrayList;

public class AoC6 extends AdventOfCodeDay {
    public AoC6(String filepath) throws IOException {
        super(filepath);
    }

    public long partASolution() {
        ArrayList<Integer> lanternFish = new ArrayList<>();

        for(String i : values.get(0).split(",")) {
            lanternFish.add(Integer.parseInt(i));
        }

        for(int i = 0; i < 80; i++) {
            for(int j = 0; j < lanternFish.size(); j++) {
                lanternFish.set(j, lanternFish.get(j) - 1);

                if(lanternFish.get(j) == -1) {
                    lanternFish.set(j, 6);
                    lanternFish.add(j, 8);
                    j++;
                }
            }
        }

        return lanternFish.size();
    }

    public long partBSolution() {
        long[] lanternFishCycles = new long[9];

        for(String i : values.get(0).split(",")) {
            lanternFishCycles[Integer.parseInt(i)] += 1;
        }

        for(int i = 0; i < 256; i++) {
            long newLanternFishCount = lanternFishCycles[0];

            for(int j = 1; j < lanternFishCycles.length; j++) {
                lanternFishCycles[j-1] = lanternFishCycles[j];
            }

            lanternFishCycles[6] += newLanternFishCount;
            lanternFishCycles[8] = newLanternFishCount;
        }

        long sum = 0;

        for(long i : lanternFishCycles) {
            sum += i;
        }

        return sum;
    }
}
