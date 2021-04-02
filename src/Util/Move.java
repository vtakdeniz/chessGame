package Util;

import Board.Tile;
import Board.Board;
import Pieces.King;
import Pieces.Piece;

import java.util.ArrayList;

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

    public boolean executeMove(){
        boolean isSuccesful=false;
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
                BoardUtil.kingTileMap.put(p.getColorName(),destinationTile);
            }
            isSuccesful=true;
            BoardUtil.executedMoves.push(this);
        }
        else {
            System.out.println("Invalid Move. Piece cannot move to selected position.");
            return isSuccesful;
        }
        /************* WARNING *****************
         *
         *    IMPLEMENT : IF CHECK METHOD HERE
         *
         */
        return isSuccesful;
    }


}
