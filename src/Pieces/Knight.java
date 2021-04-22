package Pieces;
import Board.Tile;
import Util.BoardUtil;
import Util.GameColor;
import Util.Move;

import java.util.ArrayList;
import java.util.Map;


public class Knight extends Piece{

    public int moveVector[]={ 21,19,12,8,-21,-19,-12,-8 };
    public Knight(GameColor gameColor){
       super(gameColor,"Knight",'n');
   }

    public ArrayList<Move> getPossibleMovesList(){
        this.setPossibleMoves();
        return this.possibleMoves;
    }

    public Map<Integer, Tile> getPossibleMovesMap(){
        this.setPossibleMoves();
        return this.possibleMovesMap;
    }
    public String getIconPath(){
        if (this.gameColor == GameColor.WHITE){
            return "out/production/chessGameJava/gui/icons/whiteKnight.png";
        }
        else{
            return "out/production/chessGameJava/gui/icons/blackKnight.png";
        }
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
            if (destinationTile!=null&&destinationTile.piece!=null&&destinationTile.piece.getColor()!=this.gameColor){
                this.possibleMoves.add(new Move(currentTile,destinationTile,this,true));
                this.possibleMovesMap.put(destinationTile.position,destinationTile);
            }
        }
    }
}

