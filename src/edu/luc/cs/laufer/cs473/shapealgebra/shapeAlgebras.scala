package edu.luc.cs.laufer.cs473.shapealgebra

object ShapeSize extends ShapeAlgebra[Int] {
  override def visitEllipse(e: Ellipse) = 1
  override def visitRectangle(r: Rectangle) = 1
  override def visitLocation(r: Int, l: Location) = r
  override def visitGroup(rs: Seq[Int], g: Group) = rs.sum
}

class BoundingBox extends ShapeAlgebra[Location] {
  override def visitEllipse(e: Ellipse) =
    Location(-e.halfWidth, -e.halfHeight, Rectangle(2 * e.halfWidth, 2 * e.halfHeight))
  override def visitRectangle(r: Rectangle) =
    Location(0, 0, r)
  override def visitLocation(b: Location, l: Location) = {
    Location(l.x + b.x, l.y + b.y, b.shape)
  }
  override def visitGroup(rs: Seq[Location], g: Group) = {
    val q = rs map(z => BoundingBox(z))

      def xVal(w: Location): Int = {
        return w.x
      }
      def yVal(w: Location): Int = {
        return w.y
      }
      def rightPts(w: Location): Int = {
        return w.x + w.shape.asInstanceOf[Rectangle].width
      }
      def highPts(w: Location): Int = {
        return w.y + w.shape.asInstanceOf[Rectangle].height
      }
      val minX = q.reduceLeft((s1,s2) =>  if (xVal(s2) < xVal(s1)) s2 else s1).x
      val minY = q.reduceLeft((s1,s2) =>  if (yVal(s2) < yVal(s1)) s2 else s1).y
      
      val maxX = rightPts(q.reduceLeft((s1,s2) =>  if (rightPts(s2) > rightPts(s1)) s2 else s1))
      val maxY = highPts(q.reduceLeft((s1,s2) =>  if (highPts(s2) > highPts(s1)) s2 else s1))

      val wid = (maxX-minX).abs
      val hei = (maxY-minY).abs
      new Location(minX, minY, new Rectangle(wid, hei))
  }
}

object BoundingBox extends BoundingBox