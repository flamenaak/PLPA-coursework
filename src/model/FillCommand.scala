package model

import java.awt.{Canvas, Color}

class FillCommand(fillable:FillAble) extends Command {

  override def execute(canvas: Canvas): Unit = {

    fillable.fill(canvas)
  }
}
