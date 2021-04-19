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
import Pieces.*;
import Util.*;


public class Table {
    private final JFrame gameFrame;
    private final BoardPanel boardPanel;
    private final static Dimension OUTFRAMEDIMENSION = new Dimension(900,900);
    private final static Dimension BOARD_PANEL_DIMENSION = new Dimension(700,650);
    private final static Dimension TILE_DIMENSION = new Dimension(30,30);
    private Color lightTileColor = Color.decode("#FFFFFF");
    private Color darkTileColor = Color.decode("#606000");
    private final Board chessBoard;

    private Tile selectedTile;
    private Tile startTile;
    private Tile destinationTile;
    private Piece selectedPiece;


    public Table(Board b){
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
    }

    private void setMenuBar(JMenuBar tableMenuBar){
        final JMenu fileMenu = new JMenu("File");
        final JMenuItem openPGN = new JMenuItem("Load PGN");
        openPGN.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("open pgn");
                    }
                }
        );
        fileMenu.add(openPGN);

        final JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        fileMenu.add(exitMenuItem);

        tableMenuBar.add(fileMenu);
    }


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

    private class TilePanel extends JPanel{
        private final int tileId;

        TilePanel(final BoardPanel boardPanel,final int tileId){
            super(new GridBagLayout());
            this.tileId=tileId;
            setPreferredSize(TILE_DIMENSION);
            setTileColor();
            setTileIcon(chessBoard);

            addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    destinationTile=null;
                   if(selectedTile==null){
                       selectedTile= chessBoard.getTileByInt(tileId);
                       selectedPiece=selectedTile.piece;
                       if (selectedPiece==null){
                           selectedTile=null;
                       }
                       System.out.println(tileId);
                   }
                   else{

                       destinationTile=chessBoard.getTileByInt(tileId);
                       if (destinationTile!=selectedTile){
                           //System.out.println(destinationTile.piece.getCode());
                           Move move = new Move(selectedTile,destinationTile);
                           System.out.println(tileId);
                           boolean isSuccessful=move.executeMove();
                           selectedTile=null;
                           destinationTile=null;
                           selectedPiece=null;
                           System.out.println(chessBoard.getTileByInt(16).isOccupied());
                           if(isSuccessful) System.out.println("execution successful");
                       }
                       else
                       {
                           destinationTile=null;
                       }

                      }

                   SwingUtilities.invokeLater(new Runnable() {
                       @Override
                       public void run() {
                           boardPanel.drawBoard(chessBoard);
                       }
                   });
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
            validate();
        }

        public void drawTile(final Board board){
            setTileColor();
            setTileIcon(board);
            validate();
            repaint();
        }

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

        private void setTileColor(){
            boolean isLight = ((tileId + (tileId-1)  / 8) % 2 == 0);
            setBackground(isLight ? lightTileColor : darkTileColor);
        }

    }

}
