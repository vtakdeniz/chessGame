package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Table {
    private final JFrame gameFrame;
    private final BoardPanel boardPanel;
    private final static Dimension OUTFRAMEDIMENSION = new Dimension(600,600);
    private final static Dimension BOARD_PANEL_DIMENSION = new Dimension(400,350);
    private final static Dimension TILE_DIMENSION = new Dimension(10,10);
    private Color lightTileColor = Color.decode("#FFFACD");
    private Color darkTileColor = Color.decode("#593E1A");

    public Table(){
      this.gameFrame= new JFrame("Chess Game");
      this.gameFrame.setLayout(new BorderLayout());
      final JMenuBar tableMenuBar = new JMenuBar();
      setMenuBar(tableMenuBar);
      this.gameFrame.setJMenuBar(tableMenuBar);
      this.gameFrame.setSize(OUTFRAMEDIMENSION);

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
            for (int i=0; i<64; i++){
                final TilePanel tilePanel = new TilePanel(this,i);
                this.boardTiles.add(tilePanel);
                add(tilePanel);
            }
            setPreferredSize(BOARD_PANEL_DIMENSION);
            validate();
        }
    }

    private class TilePanel extends JPanel{
        private final int tileId;

        TilePanel(final BoardPanel boardPanel,final int tileId){
            super(new GridBagLayout());
            this.tileId=tileId;
            setPreferredSize(TILE_DIMENSION);
            setTileColor();
            validate();
        }

        private void setTileIcon( ){
            this.removeAll();
        }

        private void setTileColor(){
            boolean isLight = ((tileId + tileId / 8) % 2 == 0);
            setBackground(isLight ? lightTileColor : darkTileColor);
        }

    }

}
