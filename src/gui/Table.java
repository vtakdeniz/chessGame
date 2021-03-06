package gui;
import Board.Board;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Board.Tile;
import Networking.Client;
import Networking.GameStatus;
import Pieces.*;
import Players.Player;
import Util.*;

// This is the projects gui that users interacts with.
public class Table {
    private final JFrame gameFrame;
    private static  BoardPanel boardPanel;
    private final static Dimension OUTFRAMEDIMENSION = new Dimension(750,750);
    private final static Dimension BOARD_PANEL_DIMENSION = new Dimension(700,650);
    private final static Dimension TILE_DIMENSION = new Dimension(30,30);
    private Color lightTileColor = Color.decode("#FFFFFF");
    private Color darkTileColor = Color.decode("#606000");
    private static  Board chessBoard;

    private Tile selectedTile;
    private Tile startTile;
    private Tile destinationTile;
    private Piece selectedPiece;
    public static Player player;
    public static GameColor currentPlayer;

    public Table(Board b){
        System.out.println("Table initiated");
      this.gameFrame= new JFrame("Chess Game");
      this.gameFrame.setLayout(new BorderLayout());
      final JMenuBar tableMenuBar = new JMenuBar();
      setMenuBar(tableMenuBar);
      this.gameFrame.setJMenuBar(tableMenuBar);
      this.gameFrame.setSize(OUTFRAMEDIMENSION);
      this.chessBoard=b;
      this.boardPanel=new BoardPanel();
      this.gameFrame.add(this.boardPanel,BorderLayout.CENTER);
      this.gameFrame.setVisible(true);
      currentPlayer=GameColor.WHITE;
      //Client.Start("127.0.0.1",2000);
    }

    // Changes the player that is allowed to paly
    public static void togglePlayer(){
        currentPlayer=currentPlayer.getReverse();
    }


    // Sets tables player
    public static void setPlayer(Player p){
        player=p;
        System.out.println("Player color is "+player.playerColor.name());
    }

    //Executes rival's move that got send via server
    public static void executeRivalMove(Move m){
        Move newMove= new Move(BoardUtil.IntTilesMap.get(m.startTile.position),BoardUtil.IntTilesMap.get(m.destinationTile.position));
        //boolean isSuccesfull;

        newMove.devExecuteMove();

            togglePlayer();
            chessBoard.isMoveCheck(player.playerColor.getReverse());



        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                boardPanel.drawBoard(chessBoard);
            }
        });
    }

    //Sets application menu
    private void setMenuBar(JMenuBar tableMenuBar){
        final JMenu menu = new JMenu("Menu");


        final JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menu.add(exitMenuItem);

        tableMenuBar.add(menu);
    }


    // Board panel holds collection of tiles and displays main board
    private class BoardPanel extends JPanel{
        final List<TilePanel> boardTiles;
        BoardPanel(){
            super(new GridLayout(8,8));
            this.boardTiles=new ArrayList<>();

            int num=64;
            for (int i=0; i<8; i++){
                for (int j =0;j<8;j++){
                    final TilePanel tilePanel = new TilePanel(this,num-7+j);
                    this.boardTiles.add(tilePanel);
                    add(tilePanel);
                }
                num=num-8;
            }
            setPreferredSize(BOARD_PANEL_DIMENSION);
            validate();
        }

        //Draws board to gui
        public void drawBoard(final Board board){
            removeAll();
            for (final TilePanel tilePanel:boardTiles){
                tilePanel.drawTile(board);
                add(tilePanel);
            }
            validate();
            repaint();
        }
    }


    //This class represents each tile cell in BoardPanel
    private class TilePanel extends JPanel{
        private final int tileId;
        boolean pressed;

        TilePanel(final BoardPanel boardPanel,final int tileId){
            super(new GridBagLayout());
            this.tileId=tileId;
            setPreferredSize(TILE_DIMENSION);
            setTileColor();
            setTileIcon(chessBoard);

            // Since mouse cliked won't register clicks if you move the mouse even by 1px during a click,
            //    i used combinaton of different listeners to achieve correct clicking function.
            addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {
                     pressed = true;
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (pressed) {
                        destinationTile=null;
                        if(selectedTile==null){
                            selectedTile= chessBoard.getTileByInt(tileId);
                            selectedPiece=selectedTile.piece;
                            if (selectedPiece==null){
                                selectedTile=null;
                            }
                        }
                        else{

                            destinationTile=chessBoard.getTileByInt(tileId);
                            if (destinationTile!=selectedTile&&selectedTile.piece!=null&&selectedTile!=null){
                                Move move = new Move(selectedTile,destinationTile);

                                boolean isSuccessful=false;
                                if(currentPlayer== selectedTile.piece.getColor()&&currentPlayer==player.playerColor){
                                     isSuccessful=move.executeMove();
                                }
                                if(isSuccessful){
                                    GameStatus s = new GameStatus(GameStatus.Type.Move);
                                    s.content=move;
                                    Client.Send(s);
                                    togglePlayer();

                                    if(chessBoard.isMoveCheck(player.playerColor)){

                                        System.out.println(player.playerColor+"   checked other player ");

                                        if (chessBoard.isRivalMate(player.playerColor)){
                                            System.out.println("This is is table method. Game is over.");
                                        }

                                    }
                                    else{
                                        System.out.println(player.playerColor+"  couldnt check ");
                                    }

                                }
                                selectedTile=null;
                                destinationTile=null;
                                selectedPiece=null;
                            }
                            else
                            {
                                // DEBUG
                                if (selectedTile.piece==null){
                                    System.out.println("this is in table. selected piece is null");
                                }


                                selectedTile=null;
                                destinationTile=null;
                                selectedPiece=null;
                            }

                        }

                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                boardPanel.drawBoard(chessBoard);
                            }
                        });
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {
                    pressed = false;
                }
            });
            validate();
        }

        //Draws single tile
        public void drawTile(final Board board){
            setTileColor();
            setTileIcon(board);
            validate();
            repaint();
        }

        //Sets tiles piece image
        private void setTileIcon(Board board)  {
            this.removeAll();
            if(board.getTileByInt(this.tileId).isOccupied()){
                try{
                     BufferedImage image = ImageIO.read(new File(board.getTileByInt(tileId).piece.getIconPath()));
                     add(new JLabel(new ImageIcon(image)));
                    }
                catch (IOException e){
                     e.printStackTrace();
                 }
            }
        }

        //Sets tile color based on it's position
        private void setTileColor(){
            boolean isLight = ((tileId + (tileId-1)  / 8) % 2 == 0);
            setBackground(isLight ? lightTileColor : darkTileColor);
        }

    }


    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Table(new Board());
            }
        });
    }

}
