import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class AoC8 extends AdventOfCodeDay {
    public AoC8(String filepath) throws IOException {
        super(filepath);
    }

    public long partASolution()  {
        List<String> outputs = new ArrayList<>();

        for(String i : values) {
            outputs.add(i.substring(i.indexOf("|") + 1).trim());
        }

        int sum = 0;

        for(String i : outputs) {
            for(String j : i.split(" ")) {
                if(j.length() == 2 || j.length() == 3 || j.length() == 4 || j.length() == 7) {
                    sum++;
                }
            }
        }

        return sum;
    }

    public long partBSolution()  {
        ArrayList<HashSet<String>> digitAndPositions = new ArrayList<>(10);
        int sum = 0;

        for(int i = 0; i < values.size(); i++) {
            String display = values.get(i);
            String signals = display.substring(0, display.indexOf("|")).trim();
            String output = display.substring(display.indexOf("|") + 1).trim();

            String[] segments = new String[10];
            String[] digits = signals.split(" ");

            for(int j = 0; j < digits.length; j++) {
                int digitLength = digits[j].length();

                //Sort Exclusive Values
                if(digitLength == 2) { segments[1] = digits[j]; }
                else if(digitLength == 4) { segments[4] = digits[j]; }
                else if(digitLength == 3) { segments[7] = digits[j]; }
                else if(digitLength == 7) { segments[8] = digits[j]; }
            }

            while(Arrays.asList(segments).contains(null)) {
                for(int j = 0; j < digits.length; j++) {
                    int digitLength = digits[j].length();

                    //Sort Non-Exclusive Values
                    if(digitLength == 5) {
                        if(findCharactersInString(digits[j], segments[1])) { segments[3] = digits[j]; }
                        else if(segments[6] != null && segments[9] != null && findCharactersInString(digits[j], findUniqueChar(segments[6], segments[9]))) { segments[2] = digits[j]; }
                        else if(segments[6] != null && segments[9] != null && !findCharactersInString(digits[j], findUniqueChar(segments[6], segments[9]))) { segments[5] = digits[j]; }
                    }
                    else if(digitLength == 6) {
                        if(findCharactersInString(digits[j], segments[4])) { segments[9] = digits[j]; }
                        else if(findCharactersInString(digits[j], segments[1])) { segments[0] = digits[j]; }
                        else { segments[6] = digits[j]; }
                    }
                }
            }

            String[] outputDigits = output.split(" ");
            int[] outputValues = new int[outputDigits.length];

            for(int c = 0; c < outputDigits.length; c++) {
                for(int d = 0; d < segments.length; d++) {
                    if(findCharactersInString(outputDigits[c], segments[d]) && outputDigits[c].length() == segments[d].length()) {
                        outputValues[c] = d;
                    }
                }
            }

            String stringedSum = "";

            for(int v : outputValues) {
                stringedSum += v;
            }

            sum += Integer.parseInt(stringedSum);
        }

        return sum;
    }

    public String findUniqueChar(String a, String b) {
        for(int i = 0; i < a.length(); i++) {
            if(!b.contains(a.substring(i,i+1))) {
                return a.substring(i,i+1);
            }
        }
        return "z";
    }

    public boolean findCharactersInString(String a, String b) {
        for(int i = 0; i < b.length(); i++) {
            if(!a.contains(b.substring(i,i+1))) {
                return false;
            }
        }
        return true;
    }
}
