package model

import java.awt.{Canvas, Color, Graphics}


class DrawCommand(drawable: Drawable) extends Command {


  override def execute(canvas : Canvas, plane: Plane, g: Graphics): Unit = {
    plane.transform(drawable).draw(canvas, g)
  }

}
