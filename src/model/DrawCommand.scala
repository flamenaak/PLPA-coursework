package model

import java.awt.{Canvas, Color}


class DrawCommand(drawable: Drawable) extends Command {


  override def execute(canvas : Canvas, plane: Plane): Unit = {
    plane.transform(drawable).draw(canvas)
  }

}
