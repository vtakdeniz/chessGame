import Board.Board;
import Board.Tile;
import Pieces.Piece;
import Util.Color;
import Util.Move;

import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        Color c =Color.ANSI_BLACK;

        Board b = new Board();
        b.printBoard();

        Move m = new Move(b.getTilebyString("d1"),b.getTilebyString("d4"));
        m.executeMove();
        b.printBoard();
        ArrayList a = b.getTilebyString("d2").piece.getPossibleMoves();
        for(int i=0;i<a.size();i++){
            Move move = (Move)a.get(i);
            System.out.println(move.destinationTile.position);
        }
    }
}
