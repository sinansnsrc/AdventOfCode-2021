import java.io.IOException;

public class AoC5 extends AdventOfCodeDay {
    public AoC5(String filepath) throws IOException {
        super(filepath);
    }

    public long partASolution() {
        int[][] hydrothermalVents = new int[1000][1000];

        for(String i : values) {
            int x1 = Integer.parseInt(i.substring(0, i.indexOf(",")));
            int y1 = Integer.parseInt(i.substring(i.indexOf(",") + 1, i.indexOf(" ")));

            int x2 = Integer.parseInt(i.substring(i.lastIndexOf(" ") + 1, i.lastIndexOf(",")));
            int y2 = Integer.parseInt(i.substring(i.lastIndexOf(",") + 1));

            if(x1 == x2) {
                for(int f = Math.min(y1, y2); f <= Math.max(y1, y2); f++)  {
                    hydrothermalVents[f][x1] += 1;
                }
            }
            else if(y1 == y2) {
                for(int f = Math.min(x1, x2); f <= Math.max(x1, x2); f++)  {
                    hydrothermalVents[y1][f] += 1;
                }
            }
            else {
                continue;
            }
        }

        int sum = 0;

        for(int[] a : hydrothermalVents){
            for(int b : a)  {
                if(b >= 2){
                    sum++;
                }
            }
        }

        return sum;
    }

    public long partBSolution() {
        int[][] hydrothermalVents = new int[1000][1000];

        for(String i : values) {
            int x1 = Integer.parseInt(i.substring(0, i.indexOf(",")));
            int y1 = Integer.parseInt(i.substring(i.indexOf(",") + 1, i.indexOf(" ")));

            int x2 = Integer.parseInt(i.substring(i.lastIndexOf(" ") + 1, i.lastIndexOf(",")));
            int y2 = Integer.parseInt(i.substring(i.lastIndexOf(",") + 1));

            if(x1 == x2) {
                for(int f = Math.min(y1, y2); f <= Math.max(y1, y2); f++)  {
                    hydrothermalVents[f][x1] += 1;
                }
            }
            else if(y1 == y2) {
                for(int f = Math.min(x1, x2); f <= Math.max(x1, x2); f++)  {
                    hydrothermalVents[y1][f] += 1;
                }
            }
            else {
                int m = (y2-y1)/(x2-x1);
                if(m > 0) {
                    int a = Math.min(y1, y2);
                    int b = a == y1 ? x1 : x2;
                    for(int f = 0; f <= Math.max(x1,x2) - Math.min(x1,x2); f++) {
                        hydrothermalVents[a + f][b + f] += 1;
                    }
                }
                else {
                    int a = Math.max(y1, y2);
                    int b = a == y1 ? x1 : x2;
                    for(int f = 0; f <= Math.max(x1,x2) - Math.min(x1,x2); f++) {
                        hydrothermalVents[a - f][b + f] += 1;
                    }
                }
            }
        }

        int sum = 0;

        for(int[] a : hydrothermalVents){
            for(int b : a)  {
                if(b >= 2){
                    sum++;
                }
            }
        }

        return sum;
    }
}
