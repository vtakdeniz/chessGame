package Pieces;
import Util.BoardUtil;
import Util.GameColor;
import Board.Tile;
import Util.Move;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Piece implements java.io.Serializable{
    String name;
    char code;
    boolean isPlayed=false;
    public GameColor gameColor;
    public int moveVector[];
    public ArrayList<Move> possibleMoves;
    public  Map<Integer, Tile> possibleMovesMap;

    public  Map<Integer, Tile> validMovesMap;
    public ArrayList<Move> validMoves;

    public abstract String getIconPath();



    public ArrayList<Move> getValidMovesList(){
        setValidMoves();
        return this.validMoves;
    }
    public  Map<Integer, Tile> getValidMovesMap(){
        setValidMoves();
        return this.validMovesMap;
    }

    //Valid moves are legal moves chosen from possible moves. This function cheks validity of every single possible move by
    // simulating the execution and checking whether move puts king in vulnerable position.
    public void setValidMoves(){
        validMovesMap.clear();
        validMoves.clear();

        this.setPossibleMoves();
        Piece destinationPiece;
        //Piece startTilePiece;
        for (Move move:this.possibleMoves) {
            if(move.isAttackMove){
                destinationPiece=move.destinationTile.piece;
                BoardUtil.pieceTileMap.remove(destinationPiece);
            }
            else{
                destinationPiece=null;
            }
            move.startTile.piece=null;
            move.destinationTile.piece=this;
            Map<Integer,Tile> opponentMoves=BoardUtil.getMoves(this.gameColor.getReverse());
            if (opponentMoves.get(BoardUtil.kingTileMap.get(this.gameColor).position)==null){
                validMovesMap.put(move.destinationTile.position,move.destinationTile);
                validMoves.add(move);
            }
            move.startTile.piece=this;
            move.destinationTile.piece=destinationPiece;

            if(move.isAttackMove){BoardUtil.pieceTileMap.put(destinationPiece,move.destinationTile);}

        }

        BoardUtil.setMoves(this.gameColor.getReverse());
    }

    //Possible moves of pieces are every single tile a piece can go without colliding with a different piece or
    //  getting out of chess board's bounds.
    public abstract void setPossibleMoves();

    public abstract ArrayList<Move> getPossibleMovesList();
    public abstract Map<Integer, Tile> getPossibleMovesMap();
    Piece(GameColor gameColor, String name, char code){
        this.gameColor = gameColor;
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

    public GameColor getColor(){
        return this.gameColor;
    }

    public String getColorName(){
        return this.gameColor.getColor();
    }

}
