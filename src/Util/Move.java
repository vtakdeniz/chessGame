package Util;

import Board.Tile;
import Pieces.King;
import Pieces.Pawn;
import Pieces.Piece;

import java.util.Map;

//This class checks the validity of moves and executes them by moving pieces from one tile to another

public class Move implements java.io.Serializable{
    public Tile startTile;
    public Tile destinationTile;
    public boolean isAttackMove;
    public Piece piece;
    public Move(Tile start, Tile dest){
        this.startTile=start;
        this.destinationTile=dest;
        if (destinationTile.piece!=null&&startTile.piece!=null&&destinationTile.piece.getColor()!=startTile.piece.getColor()){
            this.isAttackMove=true;
        }
    }
    public Move(Tile start, Tile dest,Piece piece,boolean isAttackMove){
        this.startTile=start;
        this.destinationTile=dest;
        this.piece=piece;
        this.isAttackMove=isAttackMove;
    }

    //Executes move without checking the validity
    public void devExecuteMove(){
        if(this.isAttackMove){
            BoardUtil.addCapturedPiece(destinationTile.piece);
            BoardUtil.pieceTileMap.remove(destinationTile.piece);
        }
        Piece p = this.startTile.piece;
        this.destinationTile.piece=p;
        this.startTile.piece=null;
        BoardUtil.pieceTileMap.put(p,destinationTile);
        if (p instanceof King){
            BoardUtil.kingTileMap.put(p.getColor(),destinationTile);
        }
        if (p instanceof Pawn&&p.getIsPlayed()==false){
            p.setIsPlayed(true);
        }
    }

    //Execute move after checking the validity
    public boolean executeMove(){
        boolean isSuccessful=false;
        Piece p = this.startTile.piece;
        if (p.getValidMovesMap().get(this.destinationTile.position)!=null){
            if(this.isAttackMove){
                BoardUtil.addCapturedPiece(destinationTile.piece);
                BoardUtil.pieceTileMap.remove(destinationTile.piece);
            }
            this.destinationTile.piece=p;
            this.startTile.piece=null;
            BoardUtil.pieceTileMap.put(p,destinationTile);
            if (p instanceof King){
                BoardUtil.kingTileMap.put(p.getColor(),destinationTile);
            }
            if (p instanceof Pawn&&p.getIsPlayed()==false){
                p.setIsPlayed(true);
            }
            isSuccessful=true;
            BoardUtil.executedMoves.push(this);
        }
        else {
            System.out.println("Invalid Move. Piece cannot move to selected position.");
            return isSuccessful;
        }

        return isSuccessful;
    }


}
