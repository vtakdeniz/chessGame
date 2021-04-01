package Pieces;
import Board.Board;
import Board.Tile;
import Util.Color;
import Util.Move;

import java.util.ArrayList;
import java.util.Map;


public class Pawn extends Piece{

    public boolean canMoveCont=false;
    public int moveVector[]={ 10 , -10};
    public int attackVector[]={ 11 ,9,-9,- 11};
    public int enPassantVector[]={20,-20};
    public Pawn(Color color){
        super(color,"Pawn",'p');
    }

    public ArrayList<Move> getPossibleMoves(){
        Map tiles = Board.IntTiles;
        Tile currentTile = Board.pieceTileMap.get(this);
        int currentPos=currentTile.position;
        int tempPos=currentPos;

        for (int i: moveVector) {
            Tile destinationTile = (Tile)tiles.get(tempPos+i);
            if (destinationTile!=null&&destinationTile.piece==null&&destinationTile.position!=(currentPos-10)){
                    this.possibleMoves.add(new Move(currentTile,destinationTile,this,false));
            }
        }

        for (int i: attackVector) {
            Tile destinationTile = (Tile)tiles.get(tempPos+i);
            if (destinationTile!=null&&destinationTile.piece!=null&&destinationTile.piece.getColor()!=this.color){
                this.possibleMoves.add(new Move(currentTile,destinationTile,this,true));
            }
        }

        if(!this.isPlayed){
            for (int i: enPassantVector) {
                Tile destinationTile = (Tile)tiles.get(tempPos+i);
                if (destinationTile!=null&&destinationTile.piece==null&&destinationTile.position!=(currentPos-10)){
                    this.possibleMoves.add(new Move(currentTile,destinationTile,this,false));
                }
            }
        }

        return this.possibleMoves;
    }
}
