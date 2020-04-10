package model

import java.awt.{Canvas, Color, Font}

case class Text(point: Point, text: String, color: Color, size: Int) extends Drawable(color) {
  def this(point: Point, text: String) = {
    this(point, text, Color.BLACK, 1)
  }

  def this(point: Point, text: String, color: Color) = {
    this(point, text, color, 1);
  }

  override def draw(canvas: Canvas): Unit = {
    val g = canvas.getGraphics()
    // canvas.getHeight() - point.y is to move 0,0 to bottom left, instead of top left which is canvas default
    g.setColor(color)
    g.setFont(g.getFont().deriveFont(size.asInstanceOf[Float]))
    g.drawString(text, point.x, canvas.getHeight() - point.y)
  }
}
