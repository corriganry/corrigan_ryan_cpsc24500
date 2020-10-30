import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JTextField;

/**
 * This class is the drawing panel that shows the pumpkin each time it is drawn
 */
class DrawingPanel extends JPanel {
    private int pumpkinWidth = 100;
    private int pumpkinHeight = 100;
    private int pumpkinLeft = 200;
    private int pumpkinTop = 100;
    private String pumpkinEyeType = "C";
    private String pumpkinNoseType = "S";
    private String pumpkinMouthType = "O";
    /**
     * Get and Set functions for all of the numbers and strings we need to draw our pumpkin
     */
    public int getPumpkinWidth() {
        return pumpkinWidth;
    }

    public void setPumpkinWidth(int pumpkinWidth) {
        this.pumpkinWidth = pumpkinWidth;
    }

    public int getPumpkinHeight() {
        return pumpkinHeight;
    }

    public void setPumpkinHeight(int pumpkinHeight) {
        this.pumpkinHeight = pumpkinHeight;
    }

    public int getPumpkinLeft() {
        return pumpkinLeft;
    }

    public void setPumpkinLeft(int pumpkinLeft) {
        this.pumpkinLeft = pumpkinLeft;
    }

    public int getPumpkinTop() {
        return pumpkinTop;
    }

    public void setPumpkinTop(int pumpkinTop) {
        this.pumpkinTop = pumpkinTop;
    }

    public String getPumpkinEyeType() {
        return pumpkinEyeType;
    }

    public void setPumpkinEyeType(String pumpkinEyeType) {
        this.pumpkinEyeType = pumpkinEyeType;
    }

    public String getPumpkinNoseType() {
        return pumpkinNoseType;
    }

    public void setPumpkinNoseType(String pumpkinNoseType) {
        this.pumpkinNoseType = pumpkinNoseType;
    }

    public String getPumpkinMouthType() {
        return pumpkinMouthType;
    }

    public void setPumpkinMouthType(String pumpkinMouthType) {
        this.pumpkinMouthType = pumpkinMouthType;
    }
    /**
     * This paints the actual pumpkin
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.ORANGE);
        g.fillOval(pumpkinLeft, pumpkinTop, pumpkinWidth, pumpkinHeight);
        g.setColor(Color.WHITE);
        g.fillRect((pumpkinLeft+pumpkinWidth - pumpkinWidth/2 - 5),(pumpkinTop-10), (10), (10));
        if (pumpkinEyeType.equalsIgnoreCase("C")) {
            g.fillOval((pumpkinLeft + 20),(pumpkinTop + 20), (20), (20));
            g.fillOval((pumpkinLeft+pumpkinWidth - 40),(pumpkinTop + 20), (20), (20));
        }
        else if (pumpkinEyeType.equalsIgnoreCase("S")) {
            g.fillRect((pumpkinLeft + 20),(pumpkinTop + 20), (20), (20));
            g.fillRect((pumpkinLeft+pumpkinWidth - 40),(pumpkinTop + 20), (20), (20));
        }
        else if (pumpkinEyeType.equalsIgnoreCase("T")) {
            g.fillPolygon(new int[] {pumpkinLeft+20, pumpkinLeft+40, pumpkinLeft+30}, new int[] {pumpkinTop+30, pumpkinTop+30, pumpkinTop+20}, 3);
            g.fillPolygon(new int[] {pumpkinLeft+60, pumpkinLeft+80, pumpkinLeft+70}, new int[] {pumpkinTop+30, pumpkinTop+30, pumpkinTop+20}, 3);
            
        }
        if (pumpkinNoseType.equalsIgnoreCase("C")) {
            g.fillOval((pumpkinLeft+pumpkinWidth - pumpkinWidth/2 - 5),(pumpkinTop + 45), (10), (10));
        }
        else if (pumpkinNoseType.equalsIgnoreCase("S")) {
            g.fillRect((pumpkinLeft+pumpkinWidth -pumpkinWidth/2 - 5),(pumpkinTop + 45), (10), (10));
        }
        else if (pumpkinNoseType.equalsIgnoreCase("T")) {
            g.fillPolygon(new int[] {pumpkinLeft+40, pumpkinLeft+60, pumpkinLeft+50}, new int[] {pumpkinTop+50, pumpkinTop+50, pumpkinTop+40}, 3);
        }
        if (pumpkinMouthType.equalsIgnoreCase("O")) {
            g.fillOval((pumpkinLeft + 20),(pumpkinTop + 70), (60), (10));
        }
        else if (pumpkinMouthType.equalsIgnoreCase("R")) {
            g.fillRect((pumpkinLeft + 20),(pumpkinTop + 70), (60), (10));
        }  

    }
}
/**
 * Class for the text panels that appear at the bottom of my JPanel
 */
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
/**
 * Class for my frame that holds all of my text fields and my drawing panel
 */
class CustomFrame extends JFrame implements ActionListener {
    public void setLook(String title, int left, int top, int width, int height) {
        setTitle(title);
        setBounds(left, top, width, height);
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        JPanel panSouth = new JPanel();
        TextPanel txtPanel = new TextPanel();
        c.add(txtPanel,BorderLayout.CENTER);
        panSouth.setLayout(new FlowLayout());
        panSouth.add(new JLabel("Left:  "));
        JTextField txtLeft = new JTextField(4);
        panSouth.add(txtLeft);
        panSouth.add(new JLabel("Top:  "));
        JTextField txtTop = new JTextField(4);
        panSouth.add(txtTop);
        panSouth.add(new JLabel("Width:  "));
        JTextField txtWidth = new JTextField(4);
        panSouth.add(txtWidth);
        panSouth.add(new JLabel("Height:  "));
        JTextField txtHeight = new JTextField(4);
        panSouth.add(txtHeight);
        panSouth.add(new JLabel("Eye (C S T):  "));
        JTextField txtEye = new JTextField(1);
        panSouth.add(txtEye);
        panSouth.add(new JLabel("Nose (C S T):  "));
        JTextField txtNose = new JTextField(1);
        panSouth.add(txtNose);
        panSouth.add(new JLabel("Mouth (O R):  "));
        JTextField txtMouth = new JTextField(1);
        panSouth.add(txtMouth);
        DrawingPanel panCenter = new DrawingPanel();
        c.add(panCenter,BorderLayout.CENTER);
        JButton btnDraw = new JButton("Draw");
        panSouth.add(btnDraw);
        btnDraw.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                try {
                    panCenter.setPumpkinEyeType(txtEye.getText());
                    panCenter.setPumpkinNoseType(txtNose.getText());
                    panCenter.setPumpkinMouthType(txtMouth.getText());
                    if (!txtEye.getText().equalsIgnoreCase("C") || !txtEye.getText().equalsIgnoreCase("S") || !txtEye.getText().equalsIgnoreCase("T") || !txtNose.getText().equalsIgnoreCase("C") || !txtNose.getText().equalsIgnoreCase("S") || !txtNose.getText().equalsIgnoreCase("T") || !txtMouth.getText().equalsIgnoreCase("O") || !txtEye.getText().equalsIgnoreCase("R")) {
                        JOptionPane.showMessageDialog(null,"You entered unrecognizable data.");
                    } else {
                    panCenter.setPumpkinLeft(Integer.parseInt(txtLeft.getText()));
                    panCenter.setPumpkinTop(Integer.parseInt(txtTop.getText()));
                    panCenter.setPumpkinWidth(Integer.parseInt(txtWidth.getText()));
                    panCenter.setPumpkinHeight(Integer.parseInt(txtHeight.getText()));
                    repaint();
                    }
                
                    
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,"You entered unrecognizable data.");
                }
            }
        });
        c.add(panSouth,BorderLayout.SOUTH);


    }
    /**
     * My custom frame
     */
    public CustomFrame() {
        setLook("Custom Frame",560, 300, 800, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}

public class PumpkinMaker {
    /**
     * main
     */
    public static void main(String[] args) {
        CustomFrame frm = new CustomFrame();
        frm.setVisible(true);
    }
}