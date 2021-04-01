package Board;

import Pieces.Piece;
import Util.Color;


public class Tile {

    public final int xcoordinate=0;
    public final int ycoordinate=0;
    protected Color color;
    public final int position;
    public Piece piece;


    public Tile(Color color, int position) {
        this.color = color;
        this.position = position;
    }

    public void printToTerminal(){

        if (piece == null){
            if (this.color==Color.ANSI_BLACK){
                System.out.print(Color.ANSI_CYAN.getAnsi()+"|***|");
            }
            else{
                System.out.print(Color.ANSI_WHITE.getAnsi()+"|---|");
            }
        }
        else{
            if (piece.getColor()==Color.ANSI_BLACK){
                System.out.print(Color.ANSI_CYAN.getAnsi()+"|*"+piece.getCode()+"*|");
            }
            else{
                System.out.print(Color.ANSI_WHITE.getAnsi()+"|-"+piece.getCode()+"-|");
            }
        }

    }


}
