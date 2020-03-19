package GUI_Model;

import model.Drawable;
import model.DrawingEngine;
import model.Line;
import model.Point;

import java.awt.*;

public class CustomCanvas extends Canvas {
    private DrawingEngine engine;

    public CustomCanvas(){
        setBackground(Color.WHITE);
        setSize(450,500);

    }

    public void paint(Graphics g)
    {
//        Point p = new Point(60,60, Color.green);
//        Point p2 = new Point(80,80, Color.green);
//        Point p3 = new Point(100,60, Color.green);
//        Point p4 = new Point(120,80, Color.green);
//        Line l = new Line(p,p2, Color.green);
//        Line l2 = new Line(p2, p3, Color.black);
//        Line l3 = new Line(p3,p4, Color.red);
//
//        l.draw(this.getGraphics());
//        l2.draw(this.getGraphics());
//        l3.draw(this.getGraphics());

        engine.draw(getGraphics());
    }

    public void setEngine(DrawingEngine e){
        engine = e;
    }
}
