import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AoC3 extends AdventOfCodeDay {

    public AoC3(String filepath) throws IOException {
        super(filepath);
    }

    public long partASolution() {
        String gammaRate = "";
        String epsilonRate = "";

        for(int i = 0; i < values.get(0).length(); i++) {
            int onesCount = 0;
            int zerosCount = 0;
            for(int j = 0; j < values.size(); j++) {
                if(values.get(j).charAt(i) == '0') {
                    zerosCount++;
                }
                else {
                    onesCount++;
                }
            }
            gammaRate += zerosCount > onesCount ? "0" : "1";
            epsilonRate += zerosCount > onesCount ? "1" : "0";
        }

        int gammaValue = Integer.parseInt(gammaRate, 2);
        int epsilonValue = Integer.parseInt(epsilonRate, 2);

        return gammaValue * epsilonValue;
    }
    
    public long partBSolution() {
        List<String> oxygenList = new ArrayList<>(values);
        List<String> co2List = new ArrayList<>(values);

        for(int i = 0; i < values.get(0).length(); i++) {
            if(oxygenList.size() == 1) {
                continue;
            }
            else {
                int onesCount = 0;
                int zerosCount = 0;
                for(int j = 0; j < oxygenList.size(); j++) {
                    if(oxygenList.get(j).charAt(i) == '0') {
                        zerosCount++;
                    }
                    else {
                        onesCount++;
                    }
                }

                char toRemove = ' ';

                if(onesCount == zerosCount) {
                    toRemove = '0';
                }
                else {
                    toRemove = onesCount > zerosCount ? '0' : '1';
                }

                for(int j = 0; j < oxygenList.size(); j++) {
                    if(oxygenList.get(j).charAt(i) == toRemove) {
                        oxygenList.remove(j);
                        j--;
                    }
                }
            }

            if(co2List.size() == 1) {
                continue;
            }
            else {
                int onesCount = 0;
                int zerosCount = 0;
                for(int j = 0; j < co2List.size(); j++) {
                    if(co2List.get(j).charAt(i) == '0') {
                        zerosCount++;
                    }
                    else {
                        onesCount++;
                    }
                }

                char toRemove = ' ';

                if(onesCount == zerosCount) {
                    toRemove = '1';
                }
                else {
                    toRemove = onesCount > zerosCount ? '1' : '0';
                }

                for(int j = 0; j < co2List.size(); j++) {
                    if(co2List.get(j).charAt(i) == toRemove) {
                        co2List.remove(j);
                        j--;
                    }
                }
            }
        }

        int oxygenValue = Integer.parseInt(oxygenList.get(0), 2);
        int co2Value = Integer.parseInt(co2List.get(0), 2);

        return oxygenValue * co2Value;
    }
}
