package Controller;

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

    public Controller() {
        gui = new GUI();
        gui.getTextArea().get_area().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if ((int) e.getKeyChar() == 10 && shift) {
                    System.out.println("parse and draw");
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

    public Drawable[] parse(String s) {
        /*
         * Parser.parse(s);
         */
        return new Drawable[0];
    }

    public void draw() {
        //gui.setEngine(new DrawingEngine(parse(gui.getTextField().getText())));
        gui.repaintCanvas();
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

        Drawable[] arr = {l, l3, l2, c ,r, t};
        gui.setEngine(new DrawingEngine(arr, new Plane(20, Color.lightGray)));
    }
}
