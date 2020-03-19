package model

import java.awt.{Canvas};

class DrawingEngine(shapes: Array[Drawable]) {
  def draw(canvas: Canvas): Unit ={
    for(shape <- shapes) shape.draw(canvas);
  }
}
