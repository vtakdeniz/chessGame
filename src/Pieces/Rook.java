package Pieces;

import Board.Board;
import Util.BoardUtil;
import Util.Color;
import Util.Move;
import Board.Tile;
import java.util.ArrayList;
import java.util.Map;


public class Rook extends Piece {

    public int moveVector[]={ 1,-1,10,-10};
    public Rook(Color color)
    {
        super(color,"Rook",'r');
    }

    public ArrayList<Move> getPossibleMoves(){
        Map tiles = BoardUtil.IntTiles;
        Tile currentTile = BoardUtil.pieceTileMap.get(this);
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
