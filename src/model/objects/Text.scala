package model.objects

import java.awt.{Canvas, Color, Graphics}

case class Text(point: Point, text: String, color: Color, size: Int) extends Drawable(color) {
  def this(point: Point, text: String) = {
    this(point, text, Color.BLACK, 1)
  }

  def this(point: Point, text: String, color: Color) = {
    this(point, text, color, 1);
  }

  override def draw(canvas: Canvas, g: Graphics): Unit = {
    // canvas.getHeight() - point.y is to move 0,0 to bottom left, instead of top left which is canvas default
    g.setColor(color)
    g.setFont(g.getFont().deriveFont(size.asInstanceOf[Float]))
    g.drawString(text, point.x, canvas.getHeight() - point.y)
  }

  override def printType(): Unit = {println(this.getClass())}

  override def toString: String = {
    return point.toString() + "\n" + "Text -> " + text + "\n" + "Text color -> " + color
  }

  override def setColor(c: Color): Drawable = {
    return new Text(point,text,c)
  }
}
