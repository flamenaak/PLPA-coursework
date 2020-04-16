package model

import java.awt.{Canvas, Color}

case class Circle (var point: Point, var radius: Int, color: Color) extends Fillable(color) {
  def this(point: Point, radius: Int) {
    this(point, radius, Color.BLACK);
  }

  override def draw(canvas: Canvas): Unit = {
    var x0 = point.x
    var y0 = point.y

    var f=1-radius
    var ddF_x=1
    var ddF_y= -2*radius
    var x=0
    var y=radius

    Point(x0, y0+radius, color).draw(canvas)
    Point(x0, y0-radius, color).draw(canvas)
    Point(x0+radius, y0, color).draw(canvas)
    Point(x0-radius, y0, color).draw(canvas)

    while(x < y)
    {
      if(f >= 0)
      {
        y-=1
        ddF_y+=2
        f+=ddF_y
      }
      x+=1
      ddF_x+=2
      f+=ddF_x

      Point(x0+x, y0+y, color).draw(canvas)
      Point(x0-x, y0+y, color).draw(canvas)
      Point(x0+x, y0-y, color).draw(canvas)
      Point(x0-x, y0-y, color).draw(canvas)
      Point(x0+y, y0+x, color).draw(canvas)
      Point(x0-y, y0+x, color).draw(canvas)
      Point(x0+y, y0-x, color).draw(canvas)
      Point(x0-y, y0-x, color).draw(canvas)
    }
  }

  override def printType(): Unit = {println(this.getClass())}

  override def toString(): String = {
    return point.toString() + "\n" + "Radius -> " + radius
  }

  override def fill(canvas: Canvas): Unit = {
    canvas.getGraphics.fillOval(point.x + radius, point.y + radius, radius*2, radius*2);
  }

  override def setColor(c: Color): Drawable = {
    return new Circle(point,radius,c)
  }
}
