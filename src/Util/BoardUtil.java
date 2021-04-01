package Util;

import Board.Board;
import Board.Tile;
import Board.Tile;
import Pieces.Piece;

import java.util.HashMap;
import java.util.Map;

public class BoardUtil {
    public static Map<String, Tile> StringTiles = new HashMap<>();
    public static Map<Integer, Tile> IntTiles = new HashMap<>();
    public static Map<Piece,Tile> pieceTileMap = new HashMap<>();
    Board board;

    public BoardUtil(Board board){
        this.board=board;
        setPieceTileMap();
        setMapping();
    }

    public void printBoard(){
        Tile tiles [][]=this.board.getTiles();
        for (int i =tiles.length-1; i>=0;i--){

            for (int j =0; j<tiles[i].length;j++){
                tiles[i][j].printToTerminal();
            }
            System.out.println();

        }
    }

    public void setMapping(){
        int tileNumber=12;
        char label;
        Tile tiles [][]=this.board.getTiles();
        for (int i =0; i<tiles.length;i++){
            label='a';
            for (int j =0; j<tiles[i].length;j++){
                if ((i+j)%2==0)
                {
                    IntTiles.put(tileNumber,tiles[i][j]);
                    StringTiles.put((label+String.valueOf(i+1)),tiles[i][j]);
                    tileNumber++;
                }
                else {
                    IntTiles.put(tileNumber,tiles[i][j]);
                    StringTiles.put((label+String.valueOf(i+1)),tiles[i][j]);
                    tileNumber++;
                }
                label++;
            }

            tileNumber+=2;
        }
    }

    public Tile getTilebyString(String tile){
        return StringTiles.get(tile);
    }

    public void setPieceTileMap(){
        Tile tiles [][]=this.board.getTiles();
        for (int i =0;i< 8;i++){
            pieceTileMap.put(tiles[0][i].piece,tiles[0][i]);
        }

        for (int i =0;i< 8;i++){
            pieceTileMap.put(tiles[7][i].piece,tiles[7][i]);
        }
        for (int i =0; i<8;i++){
            pieceTileMap.put(tiles[1][i].piece,tiles[1][i]);
            pieceTileMap.put(tiles[6][i].piece,tiles[6][i]);
        }
    }

}
