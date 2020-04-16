package model

import java.awt.{Canvas, Color}


class DrawCommand(drawable: Drawable) extends Command {


  override def execute(canvas : Canvas): Unit = {

    drawable.draw(canvas)

  }

}
