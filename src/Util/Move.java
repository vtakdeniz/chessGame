package Util;

import Board.Tile;
import Board.Board;
import Pieces.Piece;

public class Move {
    public Tile startTile;
    public Tile destinationTile;
    public Color pieceColor;
    public boolean isAttackMove;
    public Piece piece;
    public Move(Tile start, Tile dest){
        this.startTile=start;
        this.destinationTile=dest;
    }
    public Move(Tile start, Tile dest,Piece piece,boolean isAttackMove){
        this.startTile=start;
        this.destinationTile=dest;
        this.piece=piece;
        this.isAttackMove=isAttackMove;
    }

    public void executeMove(){
        Piece p = this.startTile.piece;
        this.destinationTile.piece=p;
        this.startTile.piece=null;
        Board.pieceTileMap.put(p,destinationTile);
    }


}
