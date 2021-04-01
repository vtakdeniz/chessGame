package Pieces;
import Board.Board;
import Util.Color;
import Util.Move;
import Board.Tile;

import java.util.ArrayList;
import java.util.Map;

public class Bishop extends Piece{

    public int moveVector[]={ 11,9,-9,-11};
    public boolean canMoveCont=true;
    public Bishop(Color color){
        super(color,"Bishop",'b');
    }

    public ArrayList<Move> getPossibleMoves(){
        Map tiles = Board.IntTiles;
        Tile currentTile = Board.pieceTileMap.get(this);
        int currentPos=currentTile.position;
        int tempPos=currentPos;

        int counter=1;
        for (int i: moveVector) {
            counter=1;
            Tile destinationTile = (Tile)tiles.get(tempPos+i);
            while (destinationTile!=null&&destinationTile.piece==null){
                this.possibleMoves.add(new Move(currentTile,destinationTile,this,false));
                counter++;
                destinationTile = (Tile)tiles.get(tempPos+(i*counter));
            }
            if (destinationTile!=null&&destinationTile.piece!=null&&destinationTile.piece.getColor()!=this.color){
                this.possibleMoves.add(new Move(currentTile,destinationTile,this,true));
            }
        }

        return this.possibleMoves;
    }

}
