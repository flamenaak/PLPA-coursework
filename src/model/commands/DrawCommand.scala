package model.commands

import java.awt.{Canvas, Graphics}

import model.objects.{Drawable, Plane}


class DrawCommand(drawable: Drawable) extends Command {


  override def execute(canvas : Canvas, plane: Plane, g: Graphics): Unit = {
    plane.transform(drawable).draw(canvas, g)
  }

  def getDrawable: Drawable ={
    return drawable
  }
}
