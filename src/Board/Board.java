package Board;
import Pieces.*;
import Util.Color;
import java.util.HashMap;
import java.util.Map;

public class Board {

    Tile tiles [][];
    public static Map<String, Tile> StringTiles = new HashMap<>();
    public static Map<Integer, Tile> IntTiles = new HashMap<>();
    public static Map<Piece,Tile> pieceTileMap = new HashMap<>();
    King kings[] = new King[2];


    public Board(){
        tiles = new Tile[8][8];
        setTiles();
        setPieces();
        setPieceTileMap();

    }

    public Tile getTilebyString(String tile){
      return StringTiles.get(tile);
    }

    public void setTiles(){
        int tileNumber=12;
        char label;
        for (int i =0; i<tiles.length;i++){
            label='a';
            for (int j =0; j<tiles[i].length;j++){
                if ((i+j)%2==0)
                    {
                    tiles[i][j]= new Tile(Color.ANSI_BLACK,tileNumber);
                    IntTiles.put(tileNumber,tiles[i][j]);
                    StringTiles.put((label+String.valueOf(i+1)),tiles[i][j]);
                    tileNumber++;
                     }
                else {
                    tiles[i][j]= new Tile(Color.ANSI_WHITE,tileNumber);
                    IntTiles.put(tileNumber,tiles[i][j]);
                    StringTiles.put((label+String.valueOf(i+1)),tiles[i][j]);
                    tileNumber++;
                     }
                label++;
            }

        tileNumber+=2;
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

    public void setPieceTileMap(){

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

    public void setPieces(){
        tiles[0][0].piece= new Rook(Color.ANSI_WHITE);
        tiles[0][1].piece= new Knight(Color.ANSI_WHITE);
        tiles[0][2].piece= new Bishop(Color.ANSI_WHITE);
        tiles[0][3].piece= new King(Color.ANSI_WHITE);
        kings[0]=(King)tiles[0][3].piece;
        kings[0].currentTile=tiles[0][3];
        tiles[0][4].piece= new Queen(Color.ANSI_WHITE);
        tiles[0][5].piece= new Bishop(Color.ANSI_WHITE);
        tiles[0][6].piece= new Knight(Color.ANSI_WHITE);
        tiles[0][7].piece= new Rook(Color.ANSI_WHITE);

        tiles[7][0].piece= new Rook(Color.ANSI_BLACK);
        tiles[7][1].piece= new Knight(Color.ANSI_BLACK);
        tiles[7][2].piece= new Bishop(Color.ANSI_BLACK);
        tiles[7][3].piece= new King(Color.ANSI_BLACK);
        kings[1]=(King)tiles[7][3].piece;
        kings[1].currentTile=tiles[7][3];
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
