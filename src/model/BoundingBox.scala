package model
import java.awt.{Canvas, Color}
case class BoundingBox (point_1: Point, point_3: Point, var color: Color ) extends Drawable(color)with FillAble   {

  def this(point1: Point, point2: Point) = {
    this(point1, point2, Color.BLACK)
  }

  override def draw(canvas: Canvas): Unit = {

    //todo
  }

  override def printType(): Unit = {println(this.getClass())}

  override def toString: String = {
    return point_1.toString() + "\n" + point_3.toString() + "\n" + "B-Box color -> " + color
  }

  override def fill(canvas: Canvas): Unit = {
    //TODO fill functionality

  }

  override def setColor(c: Color): Drawable = {
    return new BoundingBox(point_1,point_3,c)
  }

}
