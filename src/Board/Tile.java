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
            if (this.color==Color.BLACK){
                System.out.print(Color.ANSI_CYAN.getAnsi()+"|***|"+Color.RESET.getAnsi());
            }
            else{
                System.out.print(Color.WHITE.getAnsi()+"|---|"+Color.RESET.getAnsi());
            }
        }
        else{
            if (piece.getColor()==Color.BLACK){
                System.out.print(Color.ANSI_CYAN.getAnsi()+"| "+piece.getCode()+" |"+Color.RESET.getAnsi());
            }
            else{
                System.out.print(Color.WHITE.getAnsi()+"| "+piece.getCode()+" |"+Color.RESET.getAnsi());
            }
        }

    }


}
