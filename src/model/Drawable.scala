package model
import java.awt.{Canvas, Color, Graphics}

abstract class Drawable(color: Color) {
  def draw(canvas: Canvas): Unit
}
