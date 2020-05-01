package gui;

import model.DrawingEngine;

import java.awt.*;

public class CustomCanvas extends Canvas {
    private DrawingEngine engine;

    private GridBagConstraints _cts;

    public CustomCanvas(GridBagConstraints cts) {
        this._cts = cts;
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
}
