package model.objects

import java.awt.{Canvas, Color, Graphics}

abstract class Fillable(color: Color) extends Drawable(color) {
  def fill(canvas : Canvas, g: Graphics) : Unit
}
