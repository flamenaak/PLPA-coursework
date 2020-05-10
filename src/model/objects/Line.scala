package model.objects

import java.awt.{Canvas, Color, Graphics}

case class Line(point1: Point, point2: Point, color: Color) extends Drawable(color) {
  def this(p1: Point, p2: Point) {
    this(p1, p2, Color.BLACK)
  }

  def draw(canvas: Canvas, g: Graphics): Unit = {
    val x0 = point1.x
    val x1 = point2.x
    val y0 = point1.y
    val y1 = point2.y

    val distX = math.abs(x1 - x0)
    val stepX = if (x0 < x1) 1 else -1
    val distY = math.abs(y1 - y0)
    val stepY = if (y0 < y1) 1 else -1

    def it = new Iterator[Tuple2[Int, Int]] {
      var x = x0;
      var y = y0
      var err = (if (distX > distY) distX else -distY) / 2

      def next: (Int, Int) = {
        val res = (x, y)
        val e2 = err;
        if (e2 > -distX) {
          err -= distY;
          x += stepX
        }
        if (e2 < distY) {
          err += distX;
          y += stepY
        }
        res;
      }

      def hasNext = (stepX * x <= stepX * x1 && stepY * y <= stepY * y1)
    }

    for ((x, y) <- it) {
      val p = new Point(x, y, color)
      p.draw(canvas, g)
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
