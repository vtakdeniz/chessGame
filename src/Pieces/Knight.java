package Pieces;
import Board.Board;
import Board.Tile;
import Util.BoardUtil;
import Util.Color;
import Util.Move;

import java.util.ArrayList;
import java.util.Map;


public class Knight extends Piece{

    public int moveVector[]={ 21,19,12,8,-21,-19,-12,-8 };
    public Knight(Color color){
       super(color,"Knight",'n');
   }

    public ArrayList<Move> getPossibleMoves(){
        this.setPossibleMoves();
        return this.possibleMoves;
    }

    public Map<Integer, Tile> getPossibleMovesMap(){
        this.setPossibleMoves();
        return this.possibleMovesMap;
    }

    public void setPossibleMoves(){
        this.possibleMoves.clear();
        this.possibleMovesMap.clear();
        Map tiles = BoardUtil.IntTiles;
        Tile currentTile = BoardUtil.pieceTileMap.get(this);
        int currentPos=currentTile.position;
        int tempPos=currentPos;

        for (int i: moveVector) {
            Tile destinationTile = (Tile)tiles.get(tempPos+i);
            if (destinationTile!=null&&destinationTile.piece==null){
                this.possibleMoves.add(new Move(currentTile,destinationTile,this,false));
                this.possibleMovesMap.put(destinationTile.position,destinationTile);
                destinationTile = (Tile)tiles.get(tempPos+(i));
            }
            if (destinationTile!=null&&destinationTile.piece!=null&&destinationTile.piece.getColor()!=this.color){
                this.possibleMoves.add(new Move(currentTile,destinationTile,this,true));
                this.possibleMovesMap.put(destinationTile.position,destinationTile);
            }
        }
    }
}

