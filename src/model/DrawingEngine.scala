package model

import java.awt.Graphics;

class DrawingEngine(shapes: Array[Drawable]) {
  def draw(g: Graphics): Unit ={
    for(shape <- shapes) shape.draw(g);
  }
}
