package Pieces;
import Util.BoardUtil;
import Util.GameColor;
import Util.Move;
import Board.Tile;

import java.util.ArrayList;
import java.util.Map;

public class Bishop extends Piece{

    public int moveVector[]={ 11,9,-9,-11};

    public Bishop(GameColor gameColor){
        super(gameColor,"Bishop",'b');
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
            return "/Users/veliakdeniz/Desktop/chessGameJava/src/gui/icons/whiteBishop.png";
        }
        else{
            return "/Users/veliakdeniz/Desktop/chessGameJava/src/gui/icons/blackBishop.png";
        }
    }

    /*public void setValidMoves(){
        validMovesMap.clear();
        this.setPossibleMoves();
        Piece destinationPiece;

         for (Move move:this.possibleMoves) {
            if(move.isAttackMove){
                destinationPiece=move.destinationTile.piece;
                BoardUtil.pieceTileMap.remove(destinationPiece);
            }
            else{
                destinationPiece=null;
            }
            Piece p = move.startTile.piece;
            move.startTile.piece=null;
            move.destinationTile.piece=p;
            Map<Integer,Tile> opponentMoves=BoardUtil.getMoves(this.color.getReverse());
            if (opponentMoves.get(BoardUtil.kingTileMap.get(this.color).position)==null){
                validMovesMap.put(move.destinationTile.position,move.destinationTile);
                validMoves.add(move);
            }
            move.startTile.piece=p;
            move.destinationTile.piece=destinationPiece;

            if(move.isAttackMove){BoardUtil.pieceTileMap.put(destinationPiece,move.destinationTile);}

         }
    }*/
   /* public ArrayList<Move> getValidMovesList(){
        setValidMoves();
        return this.validMoves;
    }
    public  Map<Integer, Tile> getValidMovesMap(){
        setValidMoves();
        return this.validMovesMap;
    }*/

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
