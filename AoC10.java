import java.io.IOException;
import java.util.HashMap;

public class AoC10 extends AdventOfCodeDay {
    public AoC10(String filepath) throws IOException {
        super(filepath);
    }

    public long partASolution() {
        HashMap<Character, Character> characters = new HashMap<Character, Character>() {{
            put('(', ')');
            put('[', ']');
            put('{', '}');
            put('<', '>');
        }};


    }

    public long partBSolution() {
        return 0;
    }
}
