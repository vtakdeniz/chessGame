package Pieces;
import Util.Color;
import Board.Tile;
import Util.Move;
import com.sun.org.apache.bcel.internal.generic.RET;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Piece {
    String name;
    char code;
    boolean isPlayed=false;
    public Color color;
    public int moveVector[];
    public ArrayList<Move> possibleMoves;
    public  Map<Integer, Tile> possibleMovesMap;

    public abstract void setPossibleMoves();
    public abstract ArrayList<Move> getPossibleMoves();
    public abstract Map<Integer, Tile> getPossibleMovesMap();
    Piece(Color color, String name, char code){
        this.color=color;
        this.name=name;
        this.code=code;
        possibleMoves=new ArrayList<>();
        possibleMovesMap =new HashMap<>();
    }


    public boolean getIsPlayed() {
        return this.isPlayed;
    }

    public void setIsPlayed(boolean b) {
         this.isPlayed=b;
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

    public String getColorName(){
        return this.color.getColor();
    }

}
