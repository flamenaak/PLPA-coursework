package model

import java.awt.{Canvas, Color, Graphics}

class Point(var x: Int, var y: Int, color: Color) extends Drawable(color) {
  def this(x: Int, y: Int) {
    this(x, y, Color.BLACK)
  }

  def draw(canvas: Canvas): Unit = {
    // canvas return always new Graphics so w eneed to store it
    val g = canvas.getGraphics()
    y = canvas.getHeight() - y
    g.setColor(color)
    g.drawLine(x, y, x, y);
  }
}