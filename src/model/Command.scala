package model

import java.awt.{Canvas, Color, Graphics}

abstract class Command {
    def execute(canvas : Canvas, plane: Plane, g: Graphics) : Unit

}
