package GUI_Model;

import model.Drawable;
import model.DrawingEngine;
import model.Line;
import model.Point;

import java.awt.*;

public class CustomCanvas extends Canvas {
    private DrawingEngine engine;

    private GridBagConstraints _cts;

    public CustomCanvas(GridBagConstraints cts){
        this._cts = cts;
    }

    public GridBagConstraints get_cts() {
        return _cts;
    }

    public void set_cts(GridBagConstraints _cts) {
        this._cts = _cts;
    }

    public void paint(Graphics g)
    {
        Point p = new Point(60,60);
        Point p2 = new Point(80,80);
        Point p3 = new Point(100,60);
        Point p4 = new Point(120,80);
        Line l = new Line(p,p2, Color.green);
        Line l2 = new Line(p2, p3, Color.black);
        Line l3 = new Line(p3,p4, Color.red);

        l.draw(this);
        l2.draw(this);
        l3.draw(this);

//        engine.draw(getGraphics());
    }

    public void setEngine(DrawingEngine e){
        engine = e;
    }
}
