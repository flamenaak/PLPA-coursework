package controller;

import model.commands.Command;
import model.commands.DrawCommand;
import model.commands.FillCommand;
import model.objects.*;
import model.objects.Point;
import model.objects.Rectangle;
import parser.*;
import gui.GUI;
import model.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class Controller {
    private GUI _gui;
    private boolean shift;
    private Parser _parser;

    public Controller() {
        _gui = new GUI();
        _gui.getInputTextArea().get_area().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if ((int) e.getKeyChar() == 10 && shift) {
                    String input = _gui.getInputTextArea().get_area().getText();
                    if (!input.equals("")) {

                        _gui.clearErrors();

                        _parser = new Parser(_gui.getInputTextArea().get_area().getText(), _gui.getErrorTextArea());
                        Command[] parsed = _parser.parse();
                        BoundingBox _bbox = getBBox(parsed);
                        if (hasMoreThanOneBBox(parsed)) {
                            _gui.getErrorTextArea().get_area().append("You cannot have more than 1 Bounding-Box.\n");
                        } else {
                            _gui.setEngine(new DrawingEngine(parsed, new Plane(20, Color.lightGray), _bbox));
                            _gui.repaintCanvas();
                        }
                    } else {
                        _gui.getErrorTextArea().get_area().append("You are executing empty command.\n");
                    }
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

        _gui.showGUI();
    }

    private boolean hasMoreThanOneBBox(Command[] parsed) {
        long b_box_command_count = Arrays.stream(parsed).filter(command -> {
                    if (command instanceof DrawCommand) {
                        Drawable d = ((DrawCommand) command).getDrawable();
                        if (d instanceof BoundingBox) {
                            return true;
                        }
                    }
                    return false;
                }
        ).count();
        return b_box_command_count > 1;
    }

    private BoundingBox getBBox(Command[] parsed) {
        Command b_box = Arrays.stream(parsed).filter(command -> {
                    if (command instanceof DrawCommand) {
                        Drawable d = ((DrawCommand) command).getDrawable();
                        return (d instanceof BoundingBox);
                    }
                    return false;
                }
        ).findAny().orElse(null);
        if (b_box != null) {
            DrawCommand b_box1 = (DrawCommand) b_box;
            return (BoundingBox) b_box1.getDrawable();
        } else {
            return null;
        }
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
        Circle c2 = new Circle(new Point(10, 10), 3, Color.ORANGE);

        Command[] arr = {new DrawCommand(l), new DrawCommand(l3), new DrawCommand(l2), new DrawCommand(c), new DrawCommand(r), new DrawCommand(t), new FillCommand(c2)};

        BoundingBox bb = new BoundingBox(bp, bp2);
        _gui.setEngine(new DrawingEngine(arr, new Plane(20, Color.lightGray), bb));
    }
}
