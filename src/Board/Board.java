package Board;

import Pieces.*;
import Players.Player;
import Util.Color;

import java.util.HashMap;
import java.util.Map;



public class Board {


    Tile tiles [][];
    Player currentPlayer;
    Map<String, Tile> StringTiles = new HashMap<>();
    Map<Integer, Tile> IntTiles = new HashMap<>();

    public Board(){
        tiles = new Tile[8][8];

        setTiles();
        setPieces();
        System.out.println(StringTiles.get("h1").piece.getCode());
    }

    public void setTiles(){
        int tileNumber=12;
        char label ='a';
        for (int i =0; i<tiles.length;i++){
            label='a';
            for (int j =0; j<tiles[i].length;j++){
                if ((i+j)%2==0)
                    {
                    tiles[i][j]= new Tile(Color.ANSI_BLACK,(i+j+2));
                    IntTiles.put(tileNumber,tiles[i][j]);
                    StringTiles.put((label+String.valueOf(i+1)),tiles[i][j]);
                    System.out.print((label+String.valueOf(i+1)));
                    tileNumber++;
                     }
                else {
                    tiles[i][j]= new Tile(Color.ANSI_WHITE,(i+j+2));
                    IntTiles.put(tileNumber,tiles[i][j]);
                    StringTiles.put((label+String.valueOf(i+1)),tiles[i][j]);
                    System.out.print((label+String.valueOf(i+1)));
                    tileNumber++;
                     }
                label++;
            }
        System.out.println();

        tileNumber+=3;
        }

    }

    public void printBoard(){
         for (int i =tiles.length-1; i>=0;i--){

            for (int j =0; j<tiles[i].length;j++){
                tiles[i][j].printToTerminal();
            }
             System.out.println();

        }
    }

    public void setPieces(){
        tiles[0][0].piece= new Rook(Color.ANSI_WHITE);
        tiles[0][1].piece= new Knight(Color.ANSI_WHITE);
        tiles[0][2].piece= new Bishop(Color.ANSI_WHITE);
        tiles[0][3].piece= new King(Color.ANSI_WHITE);
        tiles[0][4].piece= new Queen(Color.ANSI_WHITE);
        tiles[0][5].piece= new Bishop(Color.ANSI_WHITE);
        tiles[0][6].piece= new Knight(Color.ANSI_WHITE);
        tiles[0][7].piece= new Rook(Color.ANSI_WHITE);

        tiles[7][0].piece= new Rook(Color.ANSI_BLACK);
        tiles[7][1].piece= new Knight(Color.ANSI_BLACK);
        tiles[7][2].piece= new Bishop(Color.ANSI_BLACK);
        tiles[7][3].piece= new King(Color.ANSI_BLACK);
        tiles[7][4].piece= new Queen(Color.ANSI_BLACK);
        tiles[7][5].piece= new Bishop(Color.ANSI_BLACK);
        tiles[7][6].piece= new Knight(Color.ANSI_BLACK);
        tiles[7][7].piece= new Rook(Color.ANSI_BLACK);

        for (int i =0; i<8;i++){
            tiles[1][i].piece=new Pawn(Color.ANSI_WHITE);
            tiles[6][i].piece=new Pawn(Color.ANSI_BLACK);
        }
    }


}
