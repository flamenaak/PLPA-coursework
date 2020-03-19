package model
import java.awt.{Color, Graphics}

abstract class Drawable(color: Color) {
  def draw(g: Graphics)
}
