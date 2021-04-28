package Board;

import Pieces.Piece;
import Util.GameColor;


public class Tile implements java.io.Serializable{

    protected GameColor gameColor;
    public final int position;
    public Piece piece;

    public Tile(GameColor gameColor, int position) {
        this.gameColor = gameColor;
        this.position = position;
    }

    public boolean isOccupied(){
        return this.piece!=null;
    }

    public void printToTerminal(){

        if (piece == null){
            if (this.gameColor == GameColor.BLACK){
                System.out.print(GameColor.ANSI_CYAN.getAnsi()+"|***|"+ GameColor.RESET.getAnsi());
            }
            else{
                System.out.print(GameColor.WHITE.getAnsi()+"|---|"+ GameColor.RESET.getAnsi());
            }
        }
        else{
            if (piece.getColor()== GameColor.BLACK){
                System.out.print(GameColor.ANSI_CYAN.getAnsi()+"| "+piece.getCode()+" |"+ GameColor.RESET.getAnsi());
            }
            else{
                System.out.print(GameColor.WHITE.getAnsi()+"| "+piece.getCode()+" |"+ GameColor.RESET.getAnsi());
            }
        }

    }


}
