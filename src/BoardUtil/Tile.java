package BoardUtil;

import Pieces.Piece;


public class Tile {

    public final int xcoordinate=0;
    public final int ycoordinate=0;
    protected String color;
    protected final int position;
    public Piece piece;


    public Tile(String color, int position) {
        this.color = color;
        this.position = position;
    }


}
