package Pieces;

import java.awt.Color;

public class Piece {
    Color color;
    String name;
    char code;
    boolean isPlayed=false;

    Piece(Color color,String name,char code){
        this.color=color;
        this.name=name;
        this.code=code;
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
