package model

import java.awt.{Canvas, Graphics};

class DrawingEngine(shapes: Array[Command], plane: Plane, boundingBox: BoundingBox) {

  def draw(canvas: Canvas): Unit ={
    plane.draw(canvas)
    val g = canvas.getGraphics()
    if(boundingBox != null){
      val bb = plane.transform(boundingBox).asInstanceOf[BoundingBox];
      var leftMost = bb.point_1
      var rightMost = bb.point_3
      val height = (leftMost.y - rightMost.y).abs
      val width = (leftMost.x - rightMost.x).abs
      if(bb.point_1.x > bb.point_3.x){
        leftMost = bb.point_3
        rightMost = bb.point_1
      }
      if (leftMost.y > rightMost.y){
        leftMost.y = rightMost.y
      }
      g.setClip(leftMost.x-1, canvas.getHeight - leftMost.y - height -1, width + 2, height + 2)
    }
    for(shape <- shapes) shape.execute(canvas, plane, g);
  }
}
