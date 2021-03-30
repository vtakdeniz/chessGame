package BoardUtil;

import Players.Player;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Board {
    Tile tiles [][]= new Tile[8][8];
    Player currentPlayer;
    Map<String, Tile> StringPieces = new HashMap<>();
    Map<Integer, Tile> IntPieces = new HashMap<>();

    public Board(){
        tiles = new Tile[8][8];

        setTiles();
        setPieces();

    }

    public void setTiles(){
    Color b = Color.BLACK;
    Color w = Color.WHITE;
        for (int i =0; i<tiles.length;i++){

            for (int j =0; j<tiles[i].length;j++){

                if ((i+j)%2==0) Tile t = new Tile(b,(i+j+2));
                else tiles[i][j].color= Color.WHITE;
            }

        }


    }

    public void printBoard(){
        for (int i =tiles.length; i>0;i--){

            for (int j =tiles[i].length; j>0;j--){

                if (tiles[i][j].color== Color.BLACK){
                    System.out.print("| * ");
                }
                else{
                    System.out.println("|  ");
                }

            }

        }
    }

    public void setPieces(){

    }


}
