package Pieces;
import Board.Tile;
import Util.BoardUtil;
import Util.GameColor;
import Util.Move;

import java.util.ArrayList;
import java.util.Map;


public class King extends Piece {

        public Tile currentTile;
        public int moveVector[]={ 11,9,-9,-11,10,-10,1,-1 };
        public King(GameColor gameColor){
            super(gameColor,"King",'k');
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
            return "out/production/chessGameJava/gui/icons/whiteKing.png";
        }
        else{
            return "out/production/chessGameJava/gui/icons/blackKing.png";
        }
    }



    public void setValidMoves(){
        validMovesMap.clear();
        validMoves.clear();

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
            move.startTile.piece=null;
            move.destinationTile.piece=this;
            BoardUtil.kingTileMap.put(this.gameColor,move.destinationTile);
            Map<Integer,Tile> opponentMoves=BoardUtil.getMoves(this.gameColor.getReverse());
            if (opponentMoves.get(BoardUtil.kingTileMap.get(this.gameColor).position)==null){
                validMovesMap.put(move.destinationTile.position,move.destinationTile);
                validMoves.add(move);
            }
            move.startTile.piece=this;
            move.destinationTile.piece=destinationPiece;
            BoardUtil.kingTileMap.put(this.gameColor,move.destinationTile);

            if(move.isAttackMove){BoardUtil.pieceTileMap.put(destinationPiece,move.destinationTile);}

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
