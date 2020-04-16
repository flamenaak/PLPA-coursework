package model

import java.awt.{Canvas};

class DrawingEngine(shapes: Array[Command], plane: Plane) {

  def draw(canvas: Canvas): Unit ={
    plane.draw(canvas)

    for(shape <- shapes) shape.execute(canvas, plane);
  }
}
