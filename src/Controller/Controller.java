package Controller;

import GUI_Model.GUI;
import model.Drawable;

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
                if((int) e.getKeyChar() == 10 && shift){
                    System.out.println("parse and draw");
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if((int) e.getKeyChar() == 65535){
                    shift = true;
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if((int) e.getKeyChar() == 65535){
                    shift = false;
                }
            }
        });

        gui.showGUI();
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
