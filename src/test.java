import Board.Board;
import Util.BoardUtil;
import Util.Color;
import Util.Move;

import java.util.ArrayList;
import java.util.Map;

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

        Move m4 = new Move(boardUtil.getTilebyString("d1"),boardUtil.getTilebyString("d2"));
        m4.executeMove();
        Move m5 = new Move(boardUtil.getTilebyString("d2"),boardUtil.getTilebyString("d3"));
        m5.executeMove();
        Move m6 = new Move(boardUtil.getTilebyString("d3"),boardUtil.getTilebyString("e3"));
        m6.executeMove();

        boardUtil.printToTerminal();

        Move m7 = new Move(boardUtil.getTilebyString("e7"),boardUtil.getTilebyString("e6"));
        m7.executeMove();
        Move m8 = new Move(boardUtil.getTilebyString("e8"),boardUtil.getTilebyString("e7"));
        m8.executeMove();
        Move m11 = new Move(boardUtil.getTilebyString("e7"),boardUtil.getTilebyString("g5"));
        m11.executeMove();

        Move m12 = new Move(boardUtil.getTilebyString("a2"),boardUtil.getTilebyString("a3"));
        boardUtil.printToTerminal();
        System.out.println(m12.isCheck());



    }
}
