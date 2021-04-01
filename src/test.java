import Board.Board;
import Board.Tile;
import Pieces.Piece;
import Util.BoardUtil;
import Util.Color;
import Util.Move;

import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        Color c =Color.ANSI_BLACK;

        Board board = new Board();
        BoardUtil boardUtil= new BoardUtil(board);

        boardUtil.printBoard();

        Move m = new Move(boardUtil.getTilebyString("e1"),boardUtil.getTilebyString("h6"));
        m.executeMove();
        boardUtil.printBoard();
        ArrayList a = boardUtil.getTilebyString("h6").piece.getPossibleMoves();
        for(int i=0;i<a.size();i++){
            Move move = (Move)a.get(i);
            System.out.println(move.destinationTile.position);
        }
    }
}
