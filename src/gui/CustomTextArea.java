package gui;


import javax.swing.*;
import java.awt.*;

public class CustomTextArea {

    private JTextArea _area;
    private GridBagConstraints _cts;

    public CustomTextArea(JTextArea _area, GridBagConstraints _cts) {
        this._area = _area;
        this._cts = _cts;
    }

    public JTextArea get_area() {
        return _area;
    }

    public void set_area(JTextArea _area) {
        this._area = _area;
    }

    public GridBagConstraints get_cts() {
        return _cts;
    }

    public void set_cts(GridBagConstraints _cts) {
        this._cts = _cts;
    }
}
