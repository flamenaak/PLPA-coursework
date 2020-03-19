package Controller;

import GUI_Model.GUI;
import model.Drawable;

public class Controller {
    private GUI gui;

    public Controller() {
        gui = new GUI();
    }

    public Drawable[] parse(String s){
        /*
         * Parser.parse(s);
         */
        return new Drawable[0];
    }

    public void draw(){
        //gui.setEngine(new DrawingEngine(parse(gui.getTextField().getText())));
        gui.repaintCanvas();
    }
}
