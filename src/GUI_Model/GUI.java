package GUI_Model;

import model.DrawingEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUI extends JFrame {


    private JPanel _mainPanel;
    private JFrame _mainFrame;
    private CustomTextArea _rightTextArea;
    private CustomTextArea _bottomTextArea;
    private CustomCanvas _canvas;


    public GUI() {

        init();
    }

    private void init() {
        _mainFrame = new JFrame();
        _mainFrame.setSize(800, 600);//frame size 300 width and 300 height
        _mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        _mainFrame.setLayout(new BoxLayout(_mainFrame.getContentPane(), BoxLayout.Y_AXIS));

        _mainPanel = new JPanel(new GridBagLayout());

        _rightTextArea = createJTextArea(1, 0, 1, 2, Color.cyan);
        _mainPanel.add(_rightTextArea.get_area(), _rightTextArea.get_cts());

        _canvas = createCanvas(0, 0, 1, 1, 400, 400); //canvas
        _canvas.setBackground(Color.WHITE);

        _mainPanel.add(_canvas, _canvas.get_cts());

        _bottomTextArea = createJTextArea(0, 1, 1, 1, Color.yellow);
        _bottomTextArea.get_area().setEditable(false);
        _mainPanel.add(_bottomTextArea.get_area(), _bottomTextArea.get_cts());

        _mainFrame.add(_mainPanel);
        _mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    }

    private CustomCanvas createCanvas(int x, int y, int w, int h, int componentWidth, int componentHeight) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        gbc.weightx = .1;
        gbc.weighty = .1;
        gbc.fill = GridBagConstraints.BOTH;
        CustomCanvas c = new CustomCanvas(gbc);
        c.setMinimumSize(new Dimension(componentWidth, componentHeight));
        c.setMaximumSize(new Dimension(componentWidth, componentHeight));
        c.setPreferredSize(new Dimension(componentWidth, componentHeight));

        return c;
    }

    private CustomTextArea createJTextArea(int x, int y, int w, int h, Color c) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        gbc.weightx = .1;
        gbc.weighty = .1;
        gbc.fill = GridBagConstraints.BOTH;
        JTextArea a = new JTextArea();
        a.setSize(new Dimension(230, 580));
        a.setMinimumSize(new Dimension(230, 580));
        a.setMaximumSize(new Dimension(230, 580));
        a.setPreferredSize(new Dimension(230, 580));
        a.setBackground(c);
        a.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        a.setLineWrap(true);

        return new CustomTextArea(a, gbc);
    }

    public void showGUI() {
        _mainFrame.setVisible(true);//now frame will be visible, by default not visible
    }

    public CustomCanvas getCanvas() {
        return _canvas;
    }

    public void setEngine(DrawingEngine e) {
        this._canvas.setEngine(e);
    }

    public void repaintCanvas() {
        _canvas.repaint();
    }

    public CustomTextArea getTextArea() {
        return _rightTextArea;
    }
}
