package model

import java.awt.{Canvas, Color}

abstract class Command {
    def execute(canvas : Canvas, plane: Plane) : Unit

}
