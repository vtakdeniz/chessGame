import Board.Board;
import Util.BoardUtil;
import Util.Color;
import Util.Move;

import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        Color c =Color.BLACK;

        Board board = new Board();
        BoardUtil boardUtil= new BoardUtil(board);

        boardUtil.printToTerminal();

        Move m = new Move(boardUtil.getTilebyString("c1"),boardUtil.getTilebyString("f4"));
        m.devExecuteMove();
        Move m2 = new Move(boardUtil.getTilebyString("e8"),boardUtil.getTilebyString("g5"));
        m2.devExecuteMove();

        Move m3 = new Move(boardUtil.getTilebyString("d1"),boardUtil.getTilebyString("e3"));
        m3.devExecuteMove();


        boardUtil.printToTerminal();

        ArrayList<Move> moves=boardUtil.getTilebyString("f4").piece.getPossibleMovesList();
        for (Move move:moves) {
            System.out.println("get possible move start : "+move.startTile.position+" -- destination : "+ move.destinationTile.position+" "+move.startTile.piece.getCode());
        }

        System.out.println("\n ******************* ");

        ArrayList<Move> moves2=boardUtil.getTilebyString("f4").piece.getValidMovesList();
        for (Move move:moves2) {
            System.out.println("get possible move start : "+move.startTile.position+" -- destination : "+ move.destinationTile.position+" "+move.startTile.piece.getCode());
        }


        ArrayList<Move> moves3=boardUtil.getTilebyString("g5").piece.getValidMovesList();
        for (Move move:moves3) {
            System.out.println("get possible move start : "+move.startTile.position+" -- destination : "+ move.destinationTile.position+" "+move.startTile.piece.getCode());
        }
    }
}
