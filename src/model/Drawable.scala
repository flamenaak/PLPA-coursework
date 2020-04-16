package model
import java.awt.{Canvas, Color}

trait FillAble  {
 // var color : Color
  def fill(canvas : Canvas) : Unit
}

abstract class Drawable(color: Color)  {
  def draw(canvas: Canvas): Unit

  def printType() : Unit

  def setColor(c:Color) : Drawable

}
