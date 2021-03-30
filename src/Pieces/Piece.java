package Pieces;



public class Piece {
    String name;
    char code;
    boolean isPlayed=false;
    int position;
    String color;



    Piece(String color, String name, char code){
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

    public String getColor(){
        return this.color;
    }

}
