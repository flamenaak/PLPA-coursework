package model

import java.awt.{Canvas, Color}

case class Rectangle (point_1: Point, point_3: Point, var color: Color ) extends Drawable(color) with FillAble {
  def this(point1: Point, point2: Point) = {
    this(point1, point2, Color.BLACK)
  }

  override def draw(canvas: Canvas): Unit = {
    var point_2 = new Point(point_3.x, point_1.y)
    var point_4 = new Point(point_1.x, point_3.y)

    var line = Line(point_1, point_2, color);
    line.draw(canvas);
    line = Line(point_2, point_3, color);
    line.draw(canvas);
    line = Line(point_3, point_4, color);
    line.draw(canvas);
    line = Line(point_4, point_1, color);
    line.draw(canvas);
  }

  override def printType(): Unit = {println(this.getClass)}


  override def fill(canvas: Canvas): Unit = {
     //todo fill functionality
  }

  override def setColor(c: Color): Drawable = {
    return new Rectangle(point_1,point_3,c)
  }
}
