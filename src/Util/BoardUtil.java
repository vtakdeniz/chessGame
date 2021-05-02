package Util;

import Board.Board;
import Board.Tile;
import Pieces.Piece;

import java.util.*;

public class BoardUtil {
    public static Map<String, Tile> StringTilesMap = new HashMap<>();
    public static Map<Integer, Tile> IntTilesMap = new HashMap<>();
    public static Map<Tile, Integer> TileIntMap = new HashMap<>();
    public static Map<Piece,Tile> pieceTileMap = new HashMap<>();
    public static Map<GameColor,Tile> kingTileMap = new HashMap<>();
    public static Stack<Piece> capturedWhitePiece = new Stack<>();
    public static Stack<Piece> capturedBlackPiece = new Stack<>();
    public static Stack<Move> executedMoves = new Stack<>();

    public static Map<Integer,Tile> allWhiteMovesMap = new HashMap<>();
    public static Map<Integer,Tile> allBlackMovesMap = new HashMap<>();

    public static Map<Integer,Tile> allWhiteValidMoves = new HashMap<>();
    public static Map<Integer,Tile> allBlackValidMoves = new HashMap<>();


    public static Map<Integer,Tile> getValidMoves(GameColor c){
        if (c== GameColor.BLACK){
            setAllValidBlackMovesMap();
            return allBlackValidMoves;
        }
        else{
            setAllValidWhiteMovesMap();
            return allWhiteValidMoves;
        }

    }

    public static void setAllValidWhiteMovesMap(){
        allWhiteValidMoves.clear();
        Piece pieces[] =pieceTileMap.keySet().toArray(new Piece[pieceTileMap.size()]);
        for (int i =0;i<pieces.length;i++){
            if (pieces[i].getColor()== GameColor.WHITE){
                allWhiteValidMoves.putAll(pieces[i].getValidMovesMap());
            }
        }
    }


       public static void setAllValidBlackMovesMap(){
           allBlackValidMoves.clear();
           Piece pieces[] =pieceTileMap.keySet().toArray(new Piece[pieceTileMap.size()]);
           for (int i =0;i<pieces.length;i++){
               if (pieces[i].getColor()== GameColor.BLACK){
                   allBlackValidMoves.putAll(pieces[i].getValidMovesMap());
               }
           }
       }

    public static Map<Integer,Tile> getMoves(GameColor c){
        if (c== GameColor.BLACK){
            setAllBlackMoves();
            return allBlackMovesMap;
        }
        else{
            setAllWhiteMoves();
            return allWhiteMovesMap;
        }
    }


    public static void setMoves(GameColor c){
        if (c== GameColor.BLACK){
            setAllBlackMoves();
        }
        else{
            setAllWhiteMoves();
        }
    }

    public static Map<Integer,Tile> getAllWhiteMovesMap(){
        setAllWhiteMoves();
        return allWhiteMovesMap;
    }

    public static Map<Integer,Tile> getAllBlackMovesMap(){
        setAllBlackMoves();
        return allBlackMovesMap;
    }

    public static void setAllWhiteMoves(){
        allWhiteMovesMap.clear();
        Piece pieces[] =pieceTileMap.keySet().toArray(new Piece[pieceTileMap.size()]);
        for (int i =0;i<pieces.length;i++){
            if (pieces[i].getColor()== GameColor.WHITE){
                allWhiteMovesMap.putAll(pieces[i].getPossibleMovesMap());
            }
        }
    }

    public static void setAllBlackMoves(){
        allBlackMovesMap.clear();
        Piece pieces[] =pieceTileMap.keySet().toArray(new Piece[pieceTileMap.size()]);
        for (int i =0;i<pieces.length;i++){
            if (pieces[i].getColor()== GameColor.BLACK){
                allBlackMovesMap.putAll(pieces[i].getPossibleMovesMap());
            }
        }
    }

    public static void addCapturedPiece(Piece p){
        if (p.gameColor == GameColor.WHITE){
            capturedWhitePiece.push(p);
        }
        else {
             capturedBlackPiece.push(p);
        }
    }

    public static Piece getCapturedPiece(GameColor c){
        if (c== GameColor.WHITE){
            return capturedWhitePiece.pop();
        }
        else {
            return capturedWhitePiece.pop();
        }
    }




    public static void printToTerminal(Board b){
        Tile tiles [][]=b.getTiles();
        int row=8;
        for (int i =tiles.length-1; i>=0;i--){
            System.out.print(row+  " :: ");
            for (int j =0; j<tiles[i].length;j++){
              tiles[i][j].printToTerminal();
            }
            row--;
            System.out.print("\n------------------------\n");

        }
        System.out.println("     | A || B || C || D || E || F || G || H |\n");
    }

    public static void setMapping(Board b){
        int tileNumber=12;
        char label;
        int positionNumber=1;
        Tile tiles [][]=b.getTiles();
        for (int i =0; i<tiles.length;i++){
            label='a';
            for (int j =0; j<tiles[i].length;j++){
                //if ((i+j)%2==0)
                //{
                    IntTilesMap.put(tileNumber,tiles[i][j]);
                    StringTilesMap.put((label+String.valueOf(i+1)),tiles[i][j]);
                    TileIntMap.put(tiles[i][j],positionNumber);
                    tileNumber++;
               /* }
                else {
                    IntTiles.put(tileNumber,tiles[i][j]);
                    StringTiles.put((label+String.valueOf(i+1)),tiles[i][j]);
                    tileNumber++;
                }*/
                label++;
            }

            tileNumber+=2;
        }
    }

    public static Tile getTilebyString(String tile){
        return StringTilesMap.get(tile);
    }

    public static void setPieceTileMap(Board b){
        Tile tiles [][]=b.getTiles();
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
