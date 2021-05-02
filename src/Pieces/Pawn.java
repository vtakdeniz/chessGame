package Pieces;
import Board.Tile;
import Util.BoardUtil;
import Util.GameColor;
import Util.Move;

import java.util.ArrayList;
import java.util.Map;


public class Pawn extends Piece{

    public int moveVector[]={ 10 , -10};
    public int attackVector[]={ 11 ,9,-9,- 11};
    public int enPassantVector[]={20,-20};
    public Pawn(GameColor gameColor){
        super(gameColor,"Pawn",'p');
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
            return "out/production/chessGameJava/gui/icons/whitePawn.png";
        }
        else{
            return "out/production/chessGameJava/gui/icons/blackPawn.png";
        }
    }


    public void setPossibleMoves(){
        this.possibleMoves.clear();
        this.possibleMovesMap.clear();
        Map tiles = BoardUtil.IntTilesMap;
        Tile currentTile = BoardUtil.pieceTileMap.get(this);
        int currentPos=currentTile.position;
        int tempPos=currentPos;

        for (int i: moveVector) {
            Tile destinationTile = (Tile)tiles.get(tempPos+i);
            if (destinationTile!=null&&destinationTile.piece==null&&
                    ((this.gameColor == GameColor.WHITE &&
                    destinationTile.position!=(currentPos-10))||this.gameColor == GameColor.BLACK &&destinationTile.position!=(currentPos+10))){
                    this.possibleMoves.add(new Move(currentTile,destinationTile,this,false));
                    this.possibleMovesMap.put(destinationTile.position,destinationTile);
            }
        }

        for (int i: attackVector) {
            Tile destinationTile = (Tile)tiles.get(tempPos+i);
            if (destinationTile!=null&&destinationTile.piece!=null&&destinationTile.piece.getColor()!=this.gameColor){
                this.possibleMoves.add(new Move(currentTile,destinationTile,this,true));
                this.possibleMovesMap.put(destinationTile.position,destinationTile);
            }
        }

        if(!this.isPlayed){
            for (int i: enPassantVector) {
                Tile destinationTile = (Tile)tiles.get(tempPos+i);
                if (destinationTile!=null&&destinationTile.piece==null&&destinationTile.position!=(currentPos-10)){
                    this.possibleMoves.add(new Move(currentTile,destinationTile,this,false));
                    this.possibleMovesMap.put(destinationTile.position,destinationTile);
                }
            }
        }
    }
}
