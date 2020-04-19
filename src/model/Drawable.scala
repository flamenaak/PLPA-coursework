package model
import java.awt.{Canvas, Color, Graphics}



abstract class Drawable(color: Color)  {
  def draw(canvas: Canvas, g: Graphics): Unit

  def printType() : Unit

  def setColor(c:Color) : Drawable

  def getSelf() : Drawable

}
