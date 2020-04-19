package model

import java.awt.{Canvas, Color, Graphics}

class FillCommand(fillable:Fillable) extends Command {

  override def execute(canvas: Canvas, plane: Plane, g: Graphics): Unit = {
    plane.transform(fillable).asInstanceOf[Fillable].fill(canvas, g)
  }
}
