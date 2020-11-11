import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

class Tile implements Serializable {
    private int shape;
    private int color;
    private int length;
    private int width;
    
    public int getShape() {
        return shape;
    }
    public int getColor() {
        return color;
    }
    public void setShape(int shape) {
        this.shape = shape;
    }
    public void setColor(int color) {
        this.color = color;
    }
    public Tile() {
        width = 175;
        length = 175;
        color = 0;
        shape = 0;
    }
    public Tile(int shape, int color) {
        setShape(shape);
        setColor(color);
    }
    public String toString() {
        return String.format("%d %d", shape, color);
    }
}


class TileWriter {
    public boolean writeToText(String fname, ArrayList<Tile> tiles) {
        File f = new File(fname);
        return writeToText(f, tiles);
    }
    public boolean writeToText(File f, ArrayList<Tile> tiles) {
        try{
            PrintWriter pw = new PrintWriter(new
                    BufferedWriter(new FileWriter(f)));
            for (Tile tile: tiles) {
                pw.println(tile);
            }
            pw.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}


class TextPanel extends JPanel {
    String text;
    int size;
    public TextPanel() {
        this("",12);
    }
    public TextPanel(String text, int size) {
        this.text = text;
        this.size = size;
    }
}

class TilePanel extends JPanel {
    private ArrayList<Tile> tiles;
    private int shape;
    private int color;
    
    public int getShape() {
        return shape;
    }
    public int getColor() {
        return color;
    }
    public void setShape(int s) {
        shape = s;
    }
    public void setColor(int c) {
        color = c;
    }
    public ArrayList<Tile> getTiles() {
        return tiles;
    }
    public void setTiles(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }
    public TilePanel() {
        tiles = new ArrayList<Tile>();
        Tile tile1 = new Tile(0,0);
        tiles.add(tile1);
        Tile tile2 = new Tile(1,4);
        tiles.add(tile2);
        Tile tile3 = new Tile(1,3);
        tiles.add(tile3);
        Tile tile4 = new Tile(1,3);
        tiles.add(tile4);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int counter = 50;
        for (Tile tile : tiles) {
            if (tile.getColor() == 0) {
                g.setColor(Color.YELLOW);
            }
            else if (tile.getColor() == 1) {
                g.setColor(Color.GREEN);
            }
            else if (tile.getColor() == 2) {
                g.setColor(Color.ORANGE);
            }
            else if (tile.getColor() == 3) {
                g.setColor(Color.RED);
            }
            else if (tile.getColor() == 4) {
                g.setColor(Color.BLUE);
            }
            if (tile.getShape() == 0) {
                g.fillOval(counter, 50, 175, 175);
            }
            else if (tile.getShape() == 1) {
                g.fillRect(counter, 50, 175, 175);
            }
            counter += 220;
    }   
        
    }
    
}


class SlotMachineFrame extends JFrame {
    private TilePanel pan;
    public void setLook(String title, int left, int top, int width, int height) {
        setTitle(title);
        setBounds(left, top, width, height);
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        pan = new TilePanel();
        c.add(pan,BorderLayout.CENTER);
        JPanel panSouth = new JPanel();
        panSouth.setLayout(new FlowLayout());
        JButton btnMax = new JButton("Max");
        panSouth.add(btnMax);
        JButton btnMid = new JButton("Mid");
        panSouth.add(btnMid);
        JButton btnMin = new JButton("Min");
        panSouth.add(btnMin);
        panSouth.add(new JLabel("$"));
        JTextField txtMoney = new JTextField(5);
        txtMoney.setText("5.00");
        panSouth.add(txtMoney);
        c.add(panSouth,BorderLayout.SOUTH);
        setupMenu();
    }
    public void setupMenu() {
        JMenuBar mbar = new JMenuBar();
        JMenu mnuFile = new JMenu("File");
        JMenuItem miSave = new JMenuItem("Save Tiles");
        miSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TileWriter tw = new TileWriter();
                JFileChooser jfc = new JFileChooser();
                if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    if (tw.writeToText(jfc.getSelectedFile(),pan.getTiles())) {
                        JOptionPane.showMessageDialog(null, "Wrote tiles information");
                    } else {
                        JOptionPane.showMessageDialog(null,"Could not write tiles information");
                    }
                }
            }
        });
        mnuFile.add(miSave);
        mbar.add(mnuFile);
        JMenu mnuHelp = new JMenu("Help");
        JMenuItem miAbout = new JMenuItem("About");
        mnuHelp.add(miAbout);
        mbar.add(mnuHelp);
        setJMenuBar(mbar);


    }
    public SlotMachineFrame() {
        setLook("Vegas Baby Vegas Slot Machine", 485, 365, 950, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}


public class SlotMachineApp {
    public static void main(String[] args) {
        SlotMachineFrame frm = new SlotMachineFrame();
        frm.setVisible(true);
    }
}
