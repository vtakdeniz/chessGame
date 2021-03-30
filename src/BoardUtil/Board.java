package BoardUtil;

import Pieces.Rook;
import Players.Player;

import java.util.HashMap;
import java.util.Map;



public class Board {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String RESET = "\033[0m";

    Tile tiles [][];
    Player currentPlayer;
    Map<String, Tile> StringPieces = new HashMap<>();
    Map<Integer, Tile> IntPieces = new HashMap<>();

    public Board(){
        tiles = new Tile[9][9];

        setTiles();
        setPieces();

    }

    public void setTiles(){
        for (int i =0; i<tiles.length;i++){
            for (int j =0; j<tiles[i].length;j++){
                if ((i+j)%2==0)
                    {
                    tiles[i][j]= new Tile("White",(i+j+2));
                     }
                else {
                    tiles[i][j]= new Tile("Black",(i+j+2));
                     }
            }

        }
        System.out.println(tiles[2][3].color);

    }

    public void printBoard(){
        for (int i =tiles.length-1; i>0;i--){
            if(i%2!=0) System.out.print(ANSI_RED+"|"+RESET);
            else System.out.print("|"+RESET);
            for (int j =tiles[i].length-1; j>0;j--){

                if (tiles[i][j].color.equals("Black")){
                    System.out.print(ANSI_RED+"|***|"+RESET);
                }
                else{
                    System.out.print("|000|");
                }

            }

            if(i%2==0) System.out.println(ANSI_RED+"|"+RESET);
            else System.out.println("|"+RESET);

        }
    }

    public void setPieces(){
        tiles[0][0].piece= new Rook("White");
    }


}
