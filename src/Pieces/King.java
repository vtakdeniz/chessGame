package Pieces;
import Board.Board;
import Board.Tile;
import Util.BoardUtil;
import Util.Color;
import Util.Move;

import java.util.ArrayList;
import java.util.Map;


public class King extends Piece {

        public Tile currentTile;
        public int moveVector[]={ 11,9,-9,-11 };
        public King(Color color){
            super(color,"King",'k');
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
        Map tiles = BoardUtil.IntTiles;
        Tile currentTile = BoardUtil.pieceTileMap.get(this);
        int currentPos=currentTile.position;
        int tempPos=currentPos;

        int counter=1;
        for (int i: moveVector) {
            counter=1;
            Tile destinationTile = (Tile)tiles.get(tempPos+i);

            while (destinationTile!=null&&destinationTile.piece==null){
                /********** WARNING *****************
                 *
                 *    IMPLEMENT : IF CHECK METHOD HERE
                 *
                 */
                this.possibleMoves.add(new Move(currentTile,destinationTile,this,false));
                this.possibleMovesMap.put(destinationTile.position,destinationTile);
                counter++;
                destinationTile = (Tile)tiles.get(tempPos+(i*counter));
            }
            if (destinationTile!=null&&destinationTile.piece!=null&&destinationTile.piece.getColor()!=this.color){
                this.possibleMoves.add(new Move(currentTile,destinationTile,this,true));
                this.possibleMovesMap.put(destinationTile.position,destinationTile);
            }
        }
    }
}
