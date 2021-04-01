package Pieces;
import Board.Board;
import Util.Color;
import Board.Tile;
import Util.Move;
import sun.jvm.hotspot.runtime.StaticBaseConstructor;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Map;

public abstract class Piece {
    String name;
    char code;
    boolean isPlayed=false;
    public Color color;
    public int moveVector[];
    public ArrayList<Move> possibleMoves;

    public abstract ArrayList<Move> getPossibleMoves();
    Piece(Color color, String name, char code){
        this.color=color;
        this.name=name;
        this.code=code;
        possibleMoves=new ArrayList<>();
    }



    public boolean isMoved(){
        return  this.isPlayed;
    }

    public char getCode(){
        return this.code;
    }

    public String getName(){
        return this.name;
    }

    public Color getColor(){
        return this.color;
    }

}
