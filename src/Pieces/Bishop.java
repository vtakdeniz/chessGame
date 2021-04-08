package Pieces;
import Board.Board;
import Util.BoardUtil;
import Util.Color;
import Util.Move;
import Board.Tile;

import java.util.ArrayList;
import java.util.Map;

public class Bishop extends Piece{

    public int moveVector[]={ 11,9,-9,-11};

    public Bishop(Color color){
        super(color,"Bishop",'b');
    }

    public ArrayList<Move> getPossibleMovesList(){
        this.setPossibleMoves();
        return this.possibleMoves;
    }

    public Map<Integer, Tile> getPossibleMovesMap(){
        this.setPossibleMoves();
        return this.possibleMovesMap;
    }

    public void setValidMoves(){
        validMovesMap.clear();
        this.setPossibleMoves();
         for (Move move:this.possibleMoves) {
            Piece p = move.startTile.piece;
            move.startTile.piece=null;
            move.destinationTile.piece=p;
            Map<Integer,Tile> opponentMoves=BoardUtil.getMoves(this.color.getReverse());
            if (opponentMoves.get(BoardUtil.kingTileMap.get(this.color).position)==null){
                System.out.println("king pos "+BoardUtil.kingTileMap.get(this.color).position);
                validMovesMap.put(move.destinationTile.position,move.destinationTile);
                validMoves.add(move);
                move.startTile.piece=p;
                move.destinationTile.piece=null;
            }
            else{
                move.startTile.piece=p;
                move.destinationTile.piece=null;
            }
         }
    }
    public ArrayList<Move> getValidMovesList(){
        setValidMoves();
        return this.validMoves;
    }
    public  Map<Integer, Tile> getValidMovesMap(){
        setValidMoves();
        return this.validMovesMap;
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
            if (destinationTile!=null&&destinationTile.piece!=null&&destinationTile.piece.getColor()!=this.color){
                this.possibleMoves.add(new Move(currentTile,destinationTile,this,true));
                this.possibleMovesMap.put(destinationTile.position,destinationTile);
            }
        }
    }

}
