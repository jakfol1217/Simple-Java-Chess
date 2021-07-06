import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Game {
    private int turn = -1;
    private static final String[] turnName = {"WHITE", "", "BLACK"};
    private static final Pattern fieldPattern = Pattern.compile("[a-hA-H][1-8]");
    private static final Map<String, Integer> fieldNames = Map.of(
            "a",1,
            "b", 2,
            "c", 3,
            "d", 4,
            "e", 5,
            "f", 6,
            "g", 7,
            "h", 8);


    //Converts the field number passed by the player to array position for ChessBoard
    public static int translateField(String position) throws Exception {
        if(fieldPattern.matcher(position).matches()) {
            String position1 = position.substring(0, 1);
            int position2 = Integer.parseInt(position.substring(1, 2));
            return fieldNames.get(position1) + (position2 - 1) * 8 - 1;
        } else{
            throw new Exception("Wrong field!");
        }

    }
}