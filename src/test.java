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

        Move m = new Move(boardUtil.getTilebyString("b2"),boardUtil.getTilebyString("b4"));
        m.executeMove();

        System.out.println("test");

        Move m23 = new Move(boardUtil.getTilebyString("c1"),boardUtil.getTilebyString("b2"));
        m23.executeMove();

        System.out.println("test2");

        Move m43 = new Move(boardUtil.getTilebyString("b2"),boardUtil.getTilebyString("e5"));
        m43.executeMove();

        System.out.println("test3");

        Move m22 = new Move(boardUtil.getTilebyString("e5"),boardUtil.getTilebyString("f4"));
        m22.executeMove();

        System.out.println("test4");



        Move m2 = new Move(boardUtil.getTilebyString("e7"),boardUtil.getTilebyString("e6"));
        m2.executeMove();

        Move m4 = new Move(boardUtil.getTilebyString("e8"),boardUtil.getTilebyString("e7"));
        m4.executeMove();

        Move m5 = new Move(boardUtil.getTilebyString("e7"),boardUtil.getTilebyString("g5"));
        m5.executeMove();

        Move m6 = new Move(boardUtil.getTilebyString("d2"),boardUtil.getTilebyString("d4"));
        m6.executeMove();

        Move m7 = new Move(boardUtil.getTilebyString("d1"),boardUtil.getTilebyString("d2"));
        m7.executeMove();

        Move m8 = new Move(boardUtil.getTilebyString("d2"),boardUtil.getTilebyString("d3"));
        m8.executeMove();

        Move m9 = new Move(boardUtil.getTilebyString("d3"),boardUtil.getTilebyString("e3"));
        m9.executeMove();

        System.out.println("DEBUG QUEEN CODE :::   "+boardUtil.getTilebyString("g5").piece.getCode());


        boardUtil.printToTerminal();

        System.out.println(boardUtil.getTilebyString("g5").piece.getCode());


        ArrayList<Move> movesqueen=boardUtil.getTilebyString("g5").piece.getPossibleMovesList();
        for (Move move:movesqueen) {
            System.out.println("get possible queen move start : "+move.startTile.position+" -- destination : "+ move.destinationTile.position+" "+move.startTile.piece.getCode());
        }


        ArrayList<Move> moves=boardUtil.getTilebyString("f4").piece.getPossibleMovesList();
        for (Move move:moves) {
            System.out.println("get possible move start : "+move.startTile.position+" -- destination : "+ move.destinationTile.position+" "+move.startTile.piece.getCode());
        }

        System.out.println("\n ******************* ");

        ArrayList<Move> moves2=boardUtil.getTilebyString("f4").piece.getValidMovesList();
        for (Move move:moves2) {
            System.out.println("get valid move start : "+move.startTile.position+" -- destination : "+ move.destinationTile.position+" "+move.startTile.piece.getCode());
        }


        System.out.println("DEBUG QUEEN CODE :::   "+boardUtil.getTilebyString("g5").piece.getCode());


        Move m233 = new Move(boardUtil.getTilebyString("g5"),boardUtil.getTilebyString("h4"));
        m233.executeMove();
        boardUtil.printToTerminal();

        ArrayList<Move> moves33=boardUtil.getTilebyString("f4").piece.getPossibleMovesList();
        for (Move move:moves33) {
            System.out.println("get possible move start : "+move.startTile.position+" -- destination : "+ move.destinationTile.position+" "+move.startTile.piece.getCode());
        }

        System.out.println("\n ******************* ");

        ArrayList<Move> moves24=boardUtil.getTilebyString("f4").piece.getValidMovesList();
        for (Move move:moves24) {
            System.out.println("get valid move start : "+move.startTile.position+" -- destination : "+ move.destinationTile.position+" "+move.startTile.piece.getCode());
        }




        Move m88 = new Move(boardUtil.getTilebyString("h4"),boardUtil.getTilebyString("g5"));
        m88.executeMove();

        Move m99 = new Move(boardUtil.getTilebyString("e1"),boardUtil.getTilebyString("f4"));
        m99.devExecuteMove();

        boardUtil.printToTerminal();


        ArrayList<Move> moves454=boardUtil.getTilebyString("f4").piece.getPossibleMovesList();
        for (Move move:moves454) {
            System.out.println("get possible move start : "+move.startTile.position+" -- destination : "+ move.destinationTile.position+" "+move.startTile.piece.getCode());
        }

        System.out.println("\n ******************* ");

        ArrayList<Move> moves55=boardUtil.getTilebyString("f4").piece.getValidMovesList();
        for (Move move:moves55) {
            System.out.println("get valid move start : "+move.startTile.position+" -- destination : "+ move.destinationTile.position+" "+move.startTile.piece.getCode());
        }



        Move m44 = new Move(boardUtil.getTilebyString("g5"),boardUtil.getTilebyString("e5"));
        m44.executeMove();


        boardUtil.printToTerminal();

        ArrayList<Move> moves333=boardUtil.getTilebyString("e3").piece.getPossibleMovesList();
        for (Move move:moves333) {
            System.out.println("get possible move start : "+move.startTile.position+" -- destination : "+ move.destinationTile.position+" "+move.startTile.piece.getCode());
        }

        System.out.println("\n ******************* ");

        ArrayList<Move> moves3334=boardUtil.getTilebyString("e3").piece.getValidMovesList();
        for (Move move:moves3334) {
            System.out.println("get valid move start : "+move.startTile.position+" -- destination : "+ move.destinationTile.position+" "+move.startTile.piece.getCode());
        }


        Move m234 = new Move(boardUtil.getTilebyString("e3"),boardUtil.getTilebyString("e4"));
        m234.executeMove();
        boardUtil.printToTerminal();



    }
}
