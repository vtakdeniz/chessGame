package Util;

import Board.Tile;
import Board.Board;
import Pieces.King;
import Pieces.Pawn;
import Pieces.Piece;

import java.util.ArrayList;
import java.util.Map;

public class Move {
    public Tile startTile;
    public Tile destinationTile;
    public boolean isAttackMove;
    public Piece piece;
    public Move(Tile start, Tile dest){
        this.startTile=start;
        this.destinationTile=dest;
        if (destinationTile.piece!=null&&destinationTile.piece.getColor()!=start.piece.getColor()){
            this.isAttackMove=true;
        }
    }
    public Move(Tile start, Tile dest,Piece piece,boolean isAttackMove){
        this.startTile=start;
        this.destinationTile=dest;
        this.piece=piece;
        this.isAttackMove=isAttackMove;
    }

    public boolean isValid(){
        return false;
    }

    public boolean isCheck(){
        Piece p = this.startTile.piece;
        this.startTile.piece=null;
        Map<Integer,Tile> opponentMoves = BoardUtil.getMoves(p.color.getReverse());
        if (opponentMoves.get(BoardUtil.kingTileMap.get(p.getColor()).position)!=null){
           this.startTile.piece=p;
           return true;
        }
        return false;
    }

    public void devExecuteMove(){
        if(this.isAttackMove){
            BoardUtil.addCapturedPiece(destinationTile.piece);
            BoardUtil.pieceTileMap.remove(destinationTile.piece);
        }
        Piece p = this.startTile.piece;
        this.destinationTile.piece=p;
        System.out.println("DEBUG POSITION ::: "+this.destinationTile.position);
        this.startTile.piece=null;
        BoardUtil.pieceTileMap.put(p,destinationTile);
        if (p instanceof King){
            BoardUtil.kingTileMap.put(p.getColor(),destinationTile);
        }
        if (p instanceof Pawn&&p.getIsPlayed()==false){
            p.setIsPlayed(true);
        }
    }

    public boolean executeMove(){
        boolean isSuccessful=false;
        Piece p = this.startTile.piece;
        if (p.getPossibleMovesMap().get(this.destinationTile.position)!=null){
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
        /************* WARNING *****************
         **
         *    IMPLEMENT : IF CHECK METHOD HERE
         *
         */
        return isSuccessful;
    }


}
