package Pieces;

import Util.BoardUtil;
import Util.GameColor;
import Util.Move;
import Board.Tile;
import java.util.ArrayList;
import java.util.Map;


public class Rook extends Piece {

    public int moveVector[]={ 1,-1,10,-10};
    public Rook(GameColor gameColor)
    {
        super(gameColor,"Rook",'r');
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
            return "out/production/chessGameJava/gui/icons/whiteRook.png";
        }
        else{
            return "out/production/chessGameJava/gui/icons/blackRook.png";
        }
    }


    public void setPossibleMoves(){
        this.possibleMoves.clear();
        this.possibleMovesMap.clear();
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
                this.possibleMovesMap.put(destinationTile.position,destinationTile);
                counter++;
                destinationTile = (Tile)tiles.get(tempPos+(i*counter));
            }
            if (destinationTile!=null&&destinationTile.piece!=null&&destinationTile.piece.getColor()!=this.gameColor){
                this.possibleMoves.add(new Move(currentTile,destinationTile,this,true));
                this.possibleMovesMap.put(destinationTile.position,destinationTile);
            }
        }
    }
}
