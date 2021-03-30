package BoardUtil;

import Pieces.Piece;

import java.awt.*;

public class Tile {

    public final int xcoordinate=0;
    public final int ycoordinate=0;
    protected Color color;
    protected final int position;
    public Piece piece;


    public Tile(Color color, int position) {
        this.color = color;
        this.position = position;
    }


}
