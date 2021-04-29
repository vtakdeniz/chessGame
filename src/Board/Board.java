package Board;
import Pieces.*;
import Util.BoardUtil;
import Util.GameColor;
import Util.Move;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

public class Board {

    Tile tiles [][];
    King kings[] = new King[2];


    public Board(){
        tiles = new Tile[8][8];
        setTiles();
        setPieces();
        BoardUtil.setMapping(this);
        BoardUtil.setPieceTileMap(this);
    }

    public int getTilePosition(Tile tile){
        return BoardUtil.TileInt.get(tile);
    }

    public boolean isMoveCheck(Move m){
        boolean isCheck=false;
        Piece p = m.destinationTile.piece;
        p.setPossibleMoves();
        Map allMoves = p.possibleMovesMap;
        System.out.println("oynayan color "+p.getColor().getColor()+"   şahı poz :   "+BoardUtil.kingTileMap.get(p.getColor().getReverse()).position);
        System.out.println("oynayan = "+p.getColor().getColor()+" ::  rakibi = "+p.getColor().getReverse().getColor());
        Iterator<Tile> itr = allMoves.values().iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next().position);
        }

        Tile kingPos = (Tile)(allMoves.get(BoardUtil.kingTileMap.get(p.getColor().getReverse()).position));

        if (kingPos!=null){
            isCheck=true;
            System.out.println("This is ischeck method in board. This is check");
        }

        return isCheck;
    }

    public void printToTerminal(){
        BoardUtil.printToTerminal(this);
    }

    public Tile getTilebyString(String tileName){
         return BoardUtil.StringTiles.get(tileName);
    }

    public Tile getTileByInt(int tilecode){
        double i=Math.floor(((tilecode-1)/8));
        double j=(tilecode-1)%8;
        return this.tiles[(int)i][(int)j];
    }

    public Tile[][] getTiles(){
        return this.tiles;
    }

    public void setTiles(){
        int tileNumber=12;
        for (int i =0; i<tiles.length;i++){
            for (int j =0; j<tiles[i].length;j++){
                if ((i+j)%2==0)
                    {
                    tiles[i][j]= new Tile(GameColor.BLACK,tileNumber);
                    tileNumber++;
                     }
                else {
                    tiles[i][j]= new Tile(GameColor.WHITE,tileNumber);
                    tileNumber++;
                     }
            }

        tileNumber+=2;
        }

    }



    public void setPieces(){
        tiles[0][0].piece= new Rook(GameColor.WHITE);
        tiles[0][1].piece= new Knight(GameColor.WHITE);
        tiles[0][2].piece= new Bishop(GameColor.WHITE);
        tiles[0][3].piece= new King(GameColor.WHITE);
        kings[0]=(King)tiles[0][3].piece;
        kings[0].currentTile=tiles[0][3];
        BoardUtil.kingTileMap.put(kings[0].gameColor,kings[0].currentTile);

        tiles[0][4].piece= new Queen(GameColor.WHITE);
        tiles[0][5].piece= new Bishop(GameColor.WHITE);
        tiles[0][6].piece= new Knight(GameColor.WHITE);
        tiles[0][7].piece= new Rook(GameColor.WHITE);

        tiles[7][0].piece= new Rook(GameColor.BLACK);
        tiles[7][1].piece= new Knight(GameColor.BLACK);
        tiles[7][2].piece= new Bishop(GameColor.BLACK);
        tiles[7][3].piece= new King(GameColor.BLACK);
        kings[1]=(King)tiles[7][3].piece;
        kings[1].currentTile=tiles[7][3];
        BoardUtil.kingTileMap.put(kings[1].gameColor,kings[1].currentTile);

        tiles[7][4].piece= new Queen(GameColor.BLACK);
        tiles[7][5].piece= new Bishop(GameColor.BLACK);
        tiles[7][6].piece= new Knight(GameColor.BLACK);
        tiles[7][7].piece= new Rook(GameColor.BLACK);


        for (int i =0; i<8;i++){
            tiles[1][i].piece=new Pawn(GameColor.WHITE);
            tiles[6][i].piece=new Pawn(GameColor.BLACK);
        }
    }


}
