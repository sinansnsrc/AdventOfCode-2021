import java.io.IOException;
import java.util.*;

public class AoC4 extends AdventOfCodeDay {

    public AoC4(String filepath) throws IOException {
        super(filepath);
    }

    public long partASolution() {
        List<Integer> drawnValues = new ArrayList<>();

        String bingoNumbers = values.remove(0);

        for(String i : bingoNumbers.split(",")) {
            drawnValues.add(Integer.parseInt(i));
        }

        HashMap<Integer, Boolean>[] bingoCards = new LinkedHashMap[values.size()/6];
        HashMap<Integer, Boolean> currentBingoCard = new LinkedHashMap<>();

        for(int i = 0; i < values.size(); i++) {
            if(i % 6 == 0) {
                currentBingoCard = new LinkedHashMap<>();
                bingoCards[i/6] = currentBingoCard;
            }
            else {
                for(String j : values.get(i).trim().replace("  ", " ").split(" ")) {
                    currentBingoCard.put(Integer.parseInt(j), false);
                }
            }
        }

        HashMap<Integer, Boolean> winningCard = null;
        int winningNumber = 0;

        for(int i = 0; i < drawnValues.size(); i++) {
            int drawnNumber = drawnValues.get(i);
            for(HashMap card : bingoCards) {
                if(card.containsKey(drawnNumber)) {
                    card.put(drawnNumber, true);
                }
            }

            for(HashMap bingoCard : bingoCards) {
                List<Integer> numbers = new ArrayList<Integer>(bingoCard.keySet());

                for(int f = 0; f < 5; f++) {
                    if(bingoCard.get(numbers.get(f * 5)).equals(true) && bingoCard.get(numbers.get(f * 5 + 1)).equals(true) && bingoCard.get(numbers.get(f * 5 + 2)).equals(true) &&
                            bingoCard.get(numbers.get(f * 5 + 3)).equals(true) && bingoCard.get(numbers.get(f * 5 + 4)).equals(true) ||
                            bingoCard.get(numbers.get(f)).equals(true) && bingoCard.get(numbers.get(f + 5)).equals(true) && bingoCard.get(numbers.get(f + 10)).equals(true) &&
                                    bingoCard.get(numbers.get(f + 15)).equals(true) && bingoCard.get(numbers.get(f + 20)).equals(true)) {
                        winningCard = bingoCard;
                        winningNumber = drawnNumber;
                    }
                }

                if(winningCard != null) {
                    break;
                }
            }

            if(winningCard != null) {
                break;
            }
        }

        int sum = 0;

        for(Integer key : winningCard.keySet()) {
            if(winningCard.get(key).equals(false)) {
                sum += key;
            }
        }

        values.add(0, bingoNumbers);

        return sum * winningNumber;
    }

    public long partBSolution() {
        List<Integer> drawnValues = new ArrayList<>();

        for(String i : values.remove(0).split(",")) {
            drawnValues.add(Integer.parseInt(i));
        }

        ArrayList<HashMap<Integer, Boolean>> bingoCards = new ArrayList<>();
        HashMap<Integer, Boolean> currentBingoCard = new LinkedHashMap<>();

        for(int i = 0; i < values.size(); i++) {
            if(i % 6 == 0) {
                currentBingoCard = new LinkedHashMap<>();
                bingoCards.add(currentBingoCard);
            }
            else {
                for(String j : values.get(i).trim().replace("  ", " ").split(" ")) {
                    currentBingoCard.put(Integer.parseInt(j), false);
                }
            }
        }

        int winningNumber = 0;
        boolean onLastCard = false;

        for(int i = 0; i < drawnValues.size(); i++) {
            if(!onLastCard) {
                int drawnNumber = drawnValues.get(i);
                for(HashMap card : bingoCards) {
                    if(card.containsKey(drawnNumber)) {
                        card.put(drawnNumber, true);
                    }
                }

                for(int a = 0; a < bingoCards.size(); a++) {
                    HashMap bingoCard = bingoCards.get(a);
                    List<Integer> numbers = new ArrayList<Integer>(bingoCard.keySet());

                    for(int f = 0; f < 5; f++) {
                        if(bingoCard.get(numbers.get(f * 5)).equals(true) && bingoCard.get(numbers.get(f * 5 + 1)).equals(true) && bingoCard.get(numbers.get(f * 5 + 2)).equals(true) &&
                                bingoCard.get(numbers.get(f * 5 + 3)).equals(true) && bingoCard.get(numbers.get(f * 5 + 4)).equals(true) ||
                                bingoCard.get(numbers.get(f)).equals(true) && bingoCard.get(numbers.get(f + 5)).equals(true) && bingoCard.get(numbers.get(f + 10)).equals(true) &&
                                        bingoCard.get(numbers.get(f + 15)).equals(true) && bingoCard.get(numbers.get(f + 20)).equals(true)) {
                            bingoCards.remove(bingoCard);
                            a--;
                            break;
                        }
                    }

                    if(bingoCards.size() == 1) {
                        onLastCard = true;
                    }
                }
            }
            else {
                int drawnNumber = drawnValues.get(i);
                if(bingoCards.get(0).containsKey(drawnNumber)) {
                    winningNumber = drawnNumber;
                    bingoCards.get(0).put(drawnNumber, true);
                    break;
                }
            }
        }

        int sum = 0;

        for(Integer key : bingoCards.get(0).keySet()) {
            if(bingoCards.get(0).get(key).equals(false)) {
                sum += key;
            }
        }

        return sum * winningNumber;
    }
}
