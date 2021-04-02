package Board;
import Pieces.*;
import Util.Color;

public class Board {

    Tile tiles [][];
    King kings[] = new King[2];


    public Board(){
        tiles = new Tile[8][8];
        setTiles();
        setPieces();
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
                    tiles[i][j]= new Tile(Color.BLACK,tileNumber);
                    tileNumber++;
                     }
                else {
                    tiles[i][j]= new Tile(Color.WHITE,tileNumber);
                    tileNumber++;
                     }
            }

        tileNumber+=2;
        }

    }



    public void setPieces(){
        tiles[0][0].piece= new Rook(Color.WHITE);
        tiles[0][1].piece= new Knight(Color.WHITE);
        tiles[0][2].piece= new Bishop(Color.WHITE);
        tiles[0][3].piece= new King(Color.WHITE);
        kings[0]=(King)tiles[0][3].piece;
        kings[0].currentTile=tiles[0][3];
        tiles[0][4].piece= new Queen(Color.WHITE);
        tiles[0][5].piece= new Bishop(Color.WHITE);
        tiles[0][6].piece= new Knight(Color.WHITE);
        tiles[0][7].piece= new Rook(Color.WHITE);

        tiles[7][0].piece= new Rook(Color.BLACK);
        tiles[7][1].piece= new Knight(Color.BLACK);
        tiles[7][2].piece= new Bishop(Color.BLACK);
        tiles[7][3].piece= new King(Color.BLACK);
        kings[1]=(King)tiles[7][3].piece;
        kings[1].currentTile=tiles[7][3];
        tiles[7][4].piece= new Queen(Color.BLACK);
        tiles[7][5].piece= new Bishop(Color.BLACK);
        tiles[7][6].piece= new Knight(Color.BLACK);
        tiles[7][7].piece= new Rook(Color.BLACK);

        for (int i =0; i<8;i++){
            tiles[1][i].piece=new Pawn(Color.WHITE);
            tiles[6][i].piece=new Pawn(Color.BLACK);
        }
    }


}
