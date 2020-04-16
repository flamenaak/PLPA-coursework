package model
import java.awt.{Canvas, Color}

abstract class Fillable(color: Color) extends Drawable(color) {
  def fill(canvas : Canvas) : Unit
}

abstract class Drawable(color: Color)  {
  def draw(canvas: Canvas): Unit

  def printType() : Unit

  def setColor(c:Color) : Drawable

}
