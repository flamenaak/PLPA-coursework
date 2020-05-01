package model.commands

import java.awt.{Canvas, Graphics}

import model.objects.{Fillable, Plane}

class FillCommand(fillable:Fillable) extends Command {

  override def execute(canvas: Canvas, plane: Plane, g: Graphics): Unit = {
    plane.transform(fillable).asInstanceOf[Fillable].fill(canvas, g)
  }

  def getFillable : Fillable = {
    return fillable
  }
}
