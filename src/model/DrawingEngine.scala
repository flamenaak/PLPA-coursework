package model

import java.awt.{Canvas};

class DrawingEngine(shapes: Array[Drawable], plane: Plane) {

  def draw(canvas: Canvas): Unit ={
    plane.draw(canvas)

    for(shape <- shapes) plane.transform(shape).draw(canvas);
  }
}
