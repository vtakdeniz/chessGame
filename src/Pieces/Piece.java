package Pieces;
import Util.Color;

import sun.jvm.hotspot.runtime.StaticBaseConstructor;

import java.security.PublicKey;

public abstract class Piece {
    String name;
    char code;
    boolean isPlayed=false;
    int position;
    public Color color;

    Piece(Color color, String name, char code){
        this.color=color;
        this.name=name;
        this.code=code;
        this.position=position;

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
