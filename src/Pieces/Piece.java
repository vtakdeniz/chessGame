package Pieces;
import Util.BoardUtil;
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

    public  Map<Integer, Tile> validMovesMap;
    public ArrayList<Move> validMoves;




    public ArrayList<Move> getValidMovesList(){
        setValidMoves();
        return this.validMoves;
    }
    public  Map<Integer, Tile> getValidMovesMap(){
        setValidMoves();
        return this.validMovesMap;
    }


    public void setValidMoves(){
        validMovesMap.clear();
        validMoves.clear();

        this.setPossibleMoves();
        Piece destinationPiece;
        Piece startTilePiece;
        for (Move move:this.possibleMoves) {
            if(move.isAttackMove){
                destinationPiece=move.destinationTile.piece;
                BoardUtil.pieceTileMap.remove(destinationPiece);
            }
            else{
                destinationPiece=null;
            }
            startTilePiece = move.startTile.piece;
            move.startTile.piece=null;
            move.destinationTile.piece=startTilePiece;
            Map<Integer,Tile> opponentMoves=BoardUtil.getMoves(this.color.getReverse());
            if (opponentMoves.get(BoardUtil.kingTileMap.get(this.color).position)==null){
                validMovesMap.put(move.destinationTile.position,move.destinationTile);
                validMoves.add(move);
            }
            move.startTile.piece=startTilePiece;
            move.destinationTile.piece=destinationPiece;

            if(move.isAttackMove){BoardUtil.pieceTileMap.put(destinationPiece,move.destinationTile);}

        }
    }


    //public abstract Map<Integer, Tile> getValidMovesMap();
    /*public  Map<Integer, Tile> getValidMovesMap(){
        return null;
    }
    public  ArrayList<Move> getValidMovesList(){
        return null;
    }
*/

    public abstract void setPossibleMoves();
    public abstract ArrayList<Move> getPossibleMovesList();
    public abstract Map<Integer, Tile> getPossibleMovesMap();
    Piece(Color color, String name, char code){
        this.color=color;
        this.name=name;
        this.code=code;
        possibleMoves=new ArrayList<>();
        possibleMovesMap =new HashMap<>();
        validMovesMap=new HashMap<>();
        validMoves=new ArrayList<>();
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
