package Networking;


public class GameStatus implements java.io.Serializable {

    public static enum Type {Status, User, UserID, Disconnect, adversaryOnline, Message, Move, SpecialMove, Undo, Board}

    public Type type;
    public Object content;

    public GameStatus(Type t) {
        this.type = t;
    }

}