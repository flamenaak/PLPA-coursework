package model

import java.awt.{Canvas, Color, Graphics}

case class Point(var x: Int, var y: Int, color: Color) extends Drawable(color) {
  def this(x: Int, y: Int) {
    this(x, y, Color.BLACK)
  }

  def draw(canvas: Canvas, g: Graphics): Unit = {
    // canvas return always new Graphics so we need to store it
    // this is to move 0,0 to bottom left, instead of top left which is canvas default
    y = canvas.getHeight() - y
    g.setColor(color)
    g.drawLine(x, y, x, y);
  }

  override def printType(): Unit = {println(this.getClass())}

  override def toString(): String = {
    return "x -> " + x + "\n" + "y ->" + y + "\n" + "Color -> " + color
  }

  override def setColor(c: Color): Drawable = {
    return new Point(x,y,c)
  }

  override def getSelf(): Drawable = this
}