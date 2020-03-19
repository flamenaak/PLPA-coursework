package GUI_Model;

import model.DrawingEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUI extends JFrame {


    private JPanel _canvasPanel;
    private JPanel _rightPanel;
    private JPanel _bottomPanel;
    private JFrame _mainFrame;
    private CustomCanvas canvas;


    public GUI() {
        this.canvas = new CustomCanvas();
        init();
    }

    private void init(){
        _mainFrame = new JFrame();
        _mainFrame.setSize(800, 600);//frame size 300 width and 300 height

        _mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout(150,150));

        _canvasPanel = createPanel(550,400,Color.yellow);
        _canvasPanel.add(canvas);
        _mainFrame.add(_canvasPanel,BorderLayout.WEST);

        _bottomPanel = createPanel(550,200,Color.green);
        _mainFrame.add(_bottomPanel,BorderLayout.SOUTH);

        _rightPanel = createPanel(250,600, Color.CYAN);
        _mainFrame.add(_rightPanel,BorderLayout.EAST);

        setLocationRelativeTo(null);
    }

    public JPanel createPanel(int width, int height, Color c){
        JPanel panel = new JPanel();
        panel.setBackground(c);
        panel.setLayout(new GridLayout());
        panel.setMinimumSize(new Dimension(width, height));
        panel.setMaximumSize(new Dimension(width, height));
        panel.setPreferredSize(new Dimension(width, height));

        return panel;
    }
    public void showGUI(){
        _mainFrame.setVisible(true);//now frame will be visible, by default not visible
    }

    public CustomCanvas getCanvas() {
        return canvas;
    }

    public void setEngine(DrawingEngine e){
        this.canvas.setEngine(e);
    }

    public void repaintCanvas(){
        canvas.repaint();
    }
}
