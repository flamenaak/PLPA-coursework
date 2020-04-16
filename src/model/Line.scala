package model

import java.awt.{Canvas, Color, Graphics}

case class Line(point1: Point, point2: Point, color: Color) extends Drawable(color) {
  def this(p1: Point, p2: Point) {
    this(p1, p2, Color.BLACK)
  }

  def draw(canvas: Canvas): Unit = {
    val x0 = point1.x
    val x1 = point2.x
    val y0 = point1.y
    val y1 = point2.y

    val dx = math.abs(x1 - x0)
    val sx = if (x0 < x1) 1 else -1
    val dy = math.abs(y1 - y0)
    val sy = if (y0 < y1) 1 else -1

    def it = new Iterator[Tuple2[Int, Int]] {
      var x = x0;
      var y = y0
      var err = (if (dx > dy) dx else -dy) / 2

      def next: (Int, Int) = {
        val res = (x, y)
        val e2 = err;
        if (e2 > -dx) {
          err -= dy;
          x += sx
        }
        if (e2 < dy) {
          err += dx;
          y += sy
        }
        res;
      }

      def hasNext = (sx * x <= sx * x1 && sy * y <= sy * y1)
    }

    for ((x, y) <- it) {
      val p = new Point(x, y, color)
      p.draw(canvas)
    }


  }

  override def printType(): Unit = {println(this.getClass())}

  override def toString: String = {
    return point1.toString() + "\n" + point2.toString() + "\n" + "Line color -> " + color
  }

  override def setColor(c: Color): Drawable = {
    return new Line(point1,point2,c)
  }
}
