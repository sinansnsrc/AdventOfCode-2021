import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AoC9 extends AdventOfCodeDay {

    public AoC9(String filepath) throws IOException {
        super(filepath);
    }

    public long partASolution() {
        int[][] vents = new int[values.get(0).length()][values.size()];

        for(int i = 0; i < values.size(); i++) {
            String[] row = values.get(i).split("");
            for(int j = 0; j < values.get(i).length(); j++) {
                vents[j][i] = Integer.parseInt(row[j]);
            }
        }

        int sum = 0;

        for(int i = 0; i < vents.length; i++) {
            for (int j = 0; j < vents[i].length; j++) {
                if (i != 0) {
                    if (!(vents[i][j] < vents[i - 1][j])) {
                        continue;
                    }
                }
                if (i != vents.length - 1) {
                    if (!(vents[i][j] < vents[i + 1][j])) {
                        continue;
                    }
                }
                if (j != 0) {
                    if (!(vents[i][j] < vents[i][j - 1])) {
                        continue;
                    }
                }
                if (j != vents[i].length - 1) {
                    if (!(vents[i][j] < vents[i][j + 1])) {
                        continue;
                    }
                }

                sum += vents[i][j] + 1;
            }
        }

        return sum;
    }

    public long partBSolution() {
        int[][] vents = new int[values.get(0).length()][values.size()];

        for(int i = 0; i < values.size(); i++) {
            String[] row = values.get(i).split("");
            for(int j = 0; j < values.get(i).length(); j++) {
                vents[j][i] = Integer.parseInt(row[j]);
            }
        }

        ArrayList<String> lowPoints = new ArrayList<>();

        for(int i = 0; i < vents.length; i++) {
            for (int j = 0; j < vents[i].length; j++) {
                if (i != 0) {
                    if (!(vents[i][j] < vents[i - 1][j])) {
                        continue;
                    }
                }
                if (i != vents.length - 1) {
                    if (!(vents[i][j] < vents[i + 1][j])) {
                        continue;
                    }
                }
                if (j != 0) {
                    if (!(vents[i][j] < vents[i][j - 1])) {
                        continue;
                    }
                }
                if (j != vents[i].length - 1) {
                    if (!(vents[i][j] < vents[i][j + 1])) {
                        continue;
                    }
                }

                lowPoints.add(i + "," + j);
            }
        }

        ArrayList<Integer> sizes = new ArrayList<>();

        for(int i = 0; i < lowPoints.size(); i++) {
            ArrayList<String> previousPositions = new ArrayList<>();
            previousPositions.add(lowPoints.get(i));
            String position = lowPoints.get(i);

            int x = Integer.parseInt(position.substring(0, position.indexOf(",")));
            int y = Integer.parseInt(position.substring(position.indexOf(",") + 1));

            sizes.add(flashFlood(x, y, vents, previousPositions).size());
        }

        Collections.sort(sizes);
        List<Integer> top3 = new ArrayList<Integer>(sizes.subList(sizes.size() -3, sizes.size()));

        return top3.get(0) * top3.get(1) * top3.get(2);
    }

    public ArrayList<String> flashFlood(int i, int j, int[][] vents, ArrayList<String> positions) {
        if (i != 0) {
            if (vents[i - 1][j] != 9 && !positions.contains((i - 1) + "," + j)) {
                positions.add(i - 1 + "," + j);
                positions = flashFlood(i - 1, j, vents, positions);
            }
        }
        if (i != vents.length - 1) {
            if (vents[i + 1][j] != 9 && !positions.contains((i + 1) + "," + j)) {
                positions.add(i + 1 + "," + j);
                positions = flashFlood(i + 1, j, vents, positions);
            }
        }
        if (j != 0) {
            if (vents[i][j - 1] != 9 && !positions.contains(i + "," + (j - 1))) {
                positions.add(i + "," + (j - 1));
                positions = flashFlood(i, j - 1, vents, positions);
            }
        }
        if (j != vents[i].length - 1) {
            if (vents[i][j + 1] != 9 && !positions.contains(i + "," + (j + 1))) {
                positions.add(i + "," + (j + 1));
                positions = flashFlood(i, j + 1, vents, positions);
            }
        }

        return positions;
    }
}
