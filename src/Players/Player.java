package Players;
import Util.GameColor;

public class Player implements java.io.Serializable {
    public GameColor playerColor;
    public String playerName;
    public int playerID;
    public Player rival;
}
