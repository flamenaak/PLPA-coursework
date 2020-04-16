package GUI_Model;

import model.BoundingBox;
import model.DrawingEngine;
import model.Plane;

import java.awt.*;

public class CustomCanvas extends Canvas {
    private DrawingEngine engine;
    private Plane plane;
    private BoundingBox boundingBox;

    private GridBagConstraints _cts;

    public CustomCanvas(GridBagConstraints cts, Plane plane) {
        this._cts = cts;
        this.plane = plane;
    }

    public GridBagConstraints get_cts() {
        return _cts;
    }

    public void set_cts(GridBagConstraints _cts) {
        this._cts = _cts;
    }

    public void paint(Graphics g) {
        if( engine != null){
            engine.draw(this);
        }
    }

    public void setEngine(DrawingEngine e) {
        engine = e;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setBoundingBox(BoundingBox boundingBox){
        this.boundingBox = boundingBox;
    }

    public BoundingBox getBoundingBox() {
        return boundingBox;
    }
}
