package model

import java.awt.{Canvas, Color, Graphics}

class Point(var x: Int, var y: Int, color: Color) extends Drawable(color) {
  def draw(g: Graphics): Unit = {
    g.setColor(color)
    g.drawLine(x, y, x, y);
  }
}