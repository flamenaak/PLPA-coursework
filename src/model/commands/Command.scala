package model.commands

import java.awt.{Canvas, Graphics}

import model.objects.Plane

abstract class Command {
    def execute(canvas : Canvas, plane: Plane, g: Graphics) : Unit
}
