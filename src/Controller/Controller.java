package Controller;

import parser.*;
import GUI_Model.GUI;
import model.*;
import model.Point;
import model.Rectangle;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller {
    private GUI gui;
    private boolean shift;
    private Parser _parser;

    public Controller() {
        gui = new GUI();

        gui.getTextArea().get_area().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if ((int) e.getKeyChar() == 10 && shift) {
                    _parser = new Parser(gui.getTextArea().get_area().getText());
                    Command[] parsed = _parser.parse();
                    System.out.println(parsed.length);
                    gui.setEngine(new DrawingEngine(parsed, new Plane(20, Color.lightGray), null));
                    gui.repaintCanvas();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if ((int) e.getKeyChar() == 65535) {
                    shift = true;
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if ((int) e.getKeyChar() == 65535) {
                    shift = false;
                }
            }
        });
        initTest();
        gui.showGUI();
    }

    public void initTest() {
        Point p = new Point(6, 6);
        Point p2 = new Point(8, 8);
        Point p3 = new Point(10, 6);
        Point p4 = new Point(12, 8);
        Line l = new Line(p, p2, Color.green);
        Line l2 = new Line(p2, p3, Color.black);
        Line l3 = new Line(p3, p4, Color.red);

        Circle c = new Circle(p, 5, Color.BLUE);
        Rectangle r = new Rectangle(p2, p3, Color.magenta);
        Text t = new Text(p4, "Test string", Color.cyan, 1);

        Point bp = new Point(1, 1);
        Point bp2 = new Point(9, 9);
        Circle c2 = new Circle(new Point(10,10), 3, Color.ORANGE);

        Command[] arr = {new DrawCommand(l), new DrawCommand(l3), new DrawCommand(l2), new DrawCommand(c), new DrawCommand(r), new DrawCommand(t), new FillCommand(c2)};

        BoundingBox bb = new BoundingBox(bp, bp2);
        gui.setEngine(new DrawingEngine(arr, new Plane(20, Color.lightGray), bb));
    }
}
