package model.objects

import java.awt.{Canvas, Color, Graphics}

case class BoundingBox (point_1: Point, point_3: Point, color: Color ) extends Fillable(color) {

  def this(point_1: Point, point_3: Point) = {
    this(point_1, point_3, Color.BLACK)
  }

  override def draw(canvas: Canvas, g: Graphics): Unit = {
    Rectangle(point_1, point_3, color).draw(canvas, g)
  }

  override def printType(): Unit = {println(this.getClass())}

  override def toString: String = {
    return point_1.toString() + "\n" + point_3.toString() + "\n" + "B-Box color -> " + color
  }

  override def fill(canvas: Canvas, g: Graphics): Unit = {
    Rectangle(point_1, point_3, color).draw(canvas, g)
  }

  override def setColor(c: Color): Drawable = {
    return new BoundingBox(point_1,point_3,c)
  }
}
