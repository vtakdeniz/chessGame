package Board;
import Pieces.*;
import Util.BoardUtil;
import Util.GameColor;
import Util.Move;

import java.util.Iterator;
import java.util.Map;

// This classes behaves as chessboard and functions as interface between tiles and move execution
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

    // returns tiles mapped position
    public int getTilePosition(Tile tile){
        return BoardUtil.TileIntMap.get(tile);
    }

    //checks whether the rival is mate after move execution
    public boolean isRivalMate(GameColor color){
        Map allMoves = BoardUtil.getValidMoves(color.getReverse());

        if (allMoves.size()==0){
            System.out.println("This is isRivalMate method in board. This is Mate");
            return true;
        }
        else {

            Iterator<Tile> itr = allMoves.values().iterator();
            while (itr.hasNext()) {
                System.out.println(itr.next().position);
            }

        }
        return false;
    }

    //checks whether move caused checl
    public boolean isMoveCheck(GameColor color){
        boolean isCheck=false;

        Map allMoves = BoardUtil.getMoves(color);

        Tile kingPos = (Tile)(allMoves.get(BoardUtil.kingTileMap.get(color.getReverse()).position));

        if (kingPos!=null){
            isCheck=true;
            System.out.println("This is isMoveCheck method in board. This is check");
        }

        return isCheck;
    }


    // prints the board to terminal
    public void printToTerminal(){
        BoardUtil.printToTerminal(this);
    }

    // returns the tile whose position given as chess coordinates like ; b2, a5, c8
    public Tile getTilebyString(String tileName){
         return BoardUtil.StringTilesMap.get(tileName);
    }

    public Tile getTileByInt(int tilecode){
        double i=Math.floor(((tilecode-1)/8));
        double j=(tilecode-1)%8;
        return this.tiles[(int)i][(int)j];
    }

    public Tile[][] getTiles(){
        return this.tiles;
    }

    // sets tiles on board and their coordinates
    public void setTiles(){
        int tileNumber=12;
        int tileCoordinate=1;
        for (int i =0; i<tiles.length;i++){
            for (int j =0; j<tiles[i].length;j++){
                if ((i+j)%2==0)
                    {
                    tiles[i][j]= new Tile(GameColor.BLACK,tileNumber,tileCoordinate);
                    tileNumber++;
                    tileCoordinate++;
                     }
                else {
                    tiles[i][j]= new Tile(GameColor.WHITE,tileNumber,tileCoordinate);
                    tileNumber++;
                    tileCoordinate++;
                     }
            }

        tileNumber+=2;
        }

    }



    //sets tiles pieces
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
