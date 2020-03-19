package model

import java.awt.{Canvas, Color, Graphics}

class Line(point1: Point, point2: Point, color: Color) extends Drawable(color) {
  def draw(g: Graphics) {
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
      p.draw(g)
    }
  }
}
