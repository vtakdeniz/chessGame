import Board.Board;
import Util.GameColor;
import gui.Table;
import Util.*;

import java.util.ArrayList;

public class test {
    public static void main(String[] args) {

        /*
        GameColor c =GameColor.BLACK;

        Board board = new Board();

        board.printToTerminal();

        Move m = new Move(board.getTilebyString("b2"),board.getTilebyString("b4"));
        m.executeMove();

        System.out.println("test");

        Move m23 = new Move(board.getTilebyString("c1"),board.getTilebyString("b2"));
        m23.executeMove();

        System.out.println("test2");

        Move m43 = new Move(board.getTilebyString("b2"),board.getTilebyString("e5"));
        m43.executeMove();

        System.out.println("test3");

        Move m22 = new Move(board.getTilebyString("e5"),board.getTilebyString("f4"));
        m22.executeMove();

        System.out.println("test4");



        Move m2 = new Move(board.getTilebyString("e7"),board.getTilebyString("e6"));
        m2.executeMove();

        Move m4 = new Move(board.getTilebyString("e8"),board.getTilebyString("e7"));
        m4.executeMove();

        Move m5 = new Move(board.getTilebyString("e7"),board.getTilebyString("g5"));
        m5.executeMove();

        Move m6 = new Move(board.getTilebyString("d2"),board.getTilebyString("d4"));
        m6.executeMove();

        Move m7 = new Move(board.getTilebyString("d1"),board.getTilebyString("d2"));
        m7.executeMove();

        Move m8 = new Move(board.getTilebyString("d2"),board.getTilebyString("d3"));
        m8.executeMove();

        Move m9 = new Move(board.getTilebyString("d3"),board.getTilebyString("e3"));
        m9.executeMove();

        System.out.println("DEBUG QUEEN CODE :::   "+board.getTilebyString("g5").piece.getCode());


        board.printToTerminal();

        System.out.println(board.getTilebyString("g5").piece.getCode());


        ArrayList<Move> movesqueen=board.getTilebyString("g5").piece.getPossibleMovesList();
        for (Move move:movesqueen) {
            System.out.println("get possible queen move start : "+move.startTile.position+" -- destination : "+ move.destinationTile.position+" "+move.startTile.piece.getCode());
        }


        ArrayList<Move> moves=board.getTilebyString("f4").piece.getPossibleMovesList();
        for (Move move:moves) {
            System.out.println("get possible move start : "+move.startTile.position+" -- destination : "+ move.destinationTile.position+" "+move.startTile.piece.getCode());
        }

        System.out.println("\n ******************* ");

        ArrayList<Move> moves2=board.getTilebyString("f4").piece.getValidMovesList();
        for (Move move:moves2) {
            System.out.println("get valid move start : "+move.startTile.position+" -- destination : "+ move.destinationTile.position+" "+move.startTile.piece.getCode());
        }


        System.out.println("DEBUG QUEEN CODE :::   "+board.getTilebyString("g5").piece.getCode());


        Move m233 = new Move(board.getTilebyString("g5"),board.getTilebyString("h4"));
        m233.executeMove();
        board.printToTerminal();

        ArrayList<Move> moves33=board.getTilebyString("f4").piece.getPossibleMovesList();
        for (Move move:moves33) {
            System.out.println("get possible move start : "+move.startTile.position+" -- destination : "+ move.destinationTile.position+" "+move.startTile.piece.getCode());
        }

        System.out.println("\n ******************* ");

        ArrayList<Move> moves24=board.getTilebyString("f4").piece.getValidMovesList();
        for (Move move:moves24) {
            System.out.println("get valid move start : "+move.startTile.position+" -- destination : "+ move.destinationTile.position+" "+move.startTile.piece.getCode());
        }




        Move m88 = new Move(board.getTilebyString("h4"),board.getTilebyString("g5"));
        m88.executeMove();

        Move m99 = new Move(board.getTilebyString("e1"),board.getTilebyString("f4"));
        m99.devExecuteMove();

        board.printToTerminal();


        ArrayList<Move> moves454=board.getTilebyString("f4").piece.getPossibleMovesList();
        for (Move move:moves454) {
            System.out.println("get possible move start : "+move.startTile.position+" -- destination : "+ move.destinationTile.position+" "+move.startTile.piece.getCode());
        }

        System.out.println("\n ******************* ");

        ArrayList<Move> moves55=board.getTilebyString("f4").piece.getValidMovesList();
        for (Move move:moves55) {
            System.out.println("get valid move start : "+move.startTile.position+" -- destination : "+ move.destinationTile.position+" "+move.startTile.piece.getCode());
        }



        Move m44 = new Move(board.getTilebyString("g5"),board.getTilebyString("e5"));
        m44.executeMove();

        board.printToTerminal();

        ArrayList<Move> moves333=board.getTilebyString("e3").piece.getPossibleMovesList();
        for (Move move:moves333) {
            System.out.println("get possible move start : "+move.startTile.position+" -- destination : "+ move.destinationTile.position+" "+move.startTile.piece.getCode());
        }

        System.out.println("\n ******************* ");

        ArrayList<Move> moves3334=board.getTilebyString("e3").piece.getValidMovesList();
        for (Move move:moves3334) {
            System.out.println("get valid move start : "+move.startTile.position+" -- destination : "+ move.destinationTile.position+" "+move.startTile.piece.getCode());
        }


        Move m234 = new Move(board.getTilebyString("e3"),board.getTilebyString("e4"));
        m234.executeMove();
        board.printToTerminal();*/

        Board b= new Board();
        Table t = new Table(b);

    }
}
