package model

import java.awt.{Canvas, Color}

class FillCommand(fillable:Fillable) extends Command {

  override def execute(canvas: Canvas, plane: Plane): Unit = {
    plane.transform(fillable).asInstanceOf[Fillable].fill(canvas)
  }
}
