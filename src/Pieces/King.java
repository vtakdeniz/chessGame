package Pieces;
import Board.Board;
import Board.Tile;
import Util.Color;
import Util.Move;

import java.util.ArrayList;
import java.util.Map;


public class King extends Piece {

        public Tile currentTile;
        public int moveVector[]={ 11,9,-9,-11 };
        public boolean canMoveCont=false;
        public King(Color color){
            super(color,"King",'k');
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
                /********** WARNING *****************
                 *
                 *    IMPLEMENT : IF CHECK METHOD HERE
                 *
                 */
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
