package model

import java.awt.{Canvas, Color, Graphics}

class Plane(unitSize: Int, color: Color) extends Drawable(color = Color.lightGray) {

  def drawCartesian(canvas: Canvas): Unit = {
    val canvasSize = new Tuple2[Int, Int](canvas.getWidth(), canvas.getHeight())
    val rows: Int = canvasSize._2 / unitSize
    val cols: Int = canvasSize._1 / unitSize

    for (i <- 0 to rows) {
      val l = new Line(new Point(0, unitSize * i), new Point(canvasSize._1, unitSize * i), color)
      l.draw(canvas);
    }

    for (i <- 0 to cols) {
      val l = new Line(new Point(unitSize * i, 0), new Point(unitSize * i, canvasSize._2), color)
      l.draw(canvas)
    }
  }

  def transform(drawable: Drawable): Drawable = drawable match {
    case Line(point1, point2, color) => new Line(transform(point1).asInstanceOf[Point], transform(point2).asInstanceOf[Point], color)
    case Point(x, y, color) => new Point(x * unitSize, y * unitSize, color)
    case Circle(point, radius, color) => new Circle(transform(point).asInstanceOf[Point], radius * unitSize, color)
    case Rectangle(point_1, point_3, color) => new Rectangle(transform(point_1).asInstanceOf[Point], transform(point_3).asInstanceOf[Point], color)
    case Text(point, string, color, size) => new Text(transform(point).asInstanceOf[Point], string, color, unitSize)
  }

  override def draw(canvas: Canvas): Unit = {
    drawCartesian(canvas)
  }
}
