package model.objects

import java.awt.{Canvas, Color, Graphics}

case class Circle (var point: Point, var radius: Int, color: Color) extends Fillable(color) {
  def this(point: Point, radius: Int) {
    this(point, radius, Color.BLACK);
  }

  override def draw(canvas: Canvas, g: Graphics): Unit = {
    var midX = point.x
    var midY = point.y

    var p=1-radius
    var ddF_x=1
    var ddF_y= -2*radius
    var x=0
    var y=radius

    // draw points at 0,90,180,270 deg
    Point(midX, midY+radius, color).draw(canvas,g)
    Point(midX, midY-radius, color).draw(canvas,g)
    Point(midX+radius, midY, color).draw(canvas,g)
    Point(midX-radius, midY, color).draw(canvas,g)

    while(x < y)
    {
      if(p >= 0) // p outside the perimeter
      {
        y-=1
        ddF_y+=2
        p+=ddF_y
      }
      x+=1
      ddF_x+=2
      p+=ddF_x

      // project into each octant
      Point(midX+x, midY+y, color).draw(canvas,g)
      Point(midX-x, midY+y, color).draw(canvas,g)
      Point(midX+x, midY-y, color).draw(canvas,g)
      Point(midX-x, midY-y, color).draw(canvas,g)
      Point(midX+y, midY+x, color).draw(canvas,g)
      Point(midX-y, midY+x, color).draw(canvas,g)
      Point(midX+y, midY-x, color).draw(canvas,g)
      Point(midX-y, midY-x, color).draw(canvas,g)
    }
  }

  override def printType(): Unit = {println(this.getClass())}

  override def toString(): String = {
    return point.toString() + "\n" + "Radius -> " + radius
  }

  override def fill(canvas: Canvas, g: Graphics): Unit = {
    g.setColor(color)
    g.fillOval(point.x - radius, canvas.getHeight - point.y - radius, radius*2, radius*2)
  }

  override def setColor(c: Color): Drawable = {
    return new Circle(point,radius,c)
  }
}
