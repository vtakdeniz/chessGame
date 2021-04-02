import Board.Board;
import Util.BoardUtil;
import Util.Color;
import Util.Move;

public class test {
    public static void main(String[] args) {
        Color c =Color.BLACK;

        Board board = new Board();
        BoardUtil boardUtil= new BoardUtil(board);

        boardUtil.printToTerminal();

        Move m = new Move(boardUtil.getTilebyString("d2"),boardUtil.getTilebyString("d4"));
        m.executeMove();

        Move m2 = new Move(boardUtil.getTilebyString("c1"),boardUtil.getTilebyString("h6"));
        m2.executeMove();

        Move m3 = new Move(boardUtil.getTilebyString("h6"),boardUtil.getTilebyString("g7"));
        m3.executeMove();

        boardUtil.printToTerminal();
        System.out.println(BoardUtil.capturedBlackPiece.get(0).getCode());
        /*
        ArrayList a = boardUtil.getTilebyString("a2").piece.getPossibleMoves();
        for(int i=0;i<a.size();i++){
            Move move = (Move)a.get(i);
            System.out.println(move.destinationTile.position);
        }*/

    }
}
