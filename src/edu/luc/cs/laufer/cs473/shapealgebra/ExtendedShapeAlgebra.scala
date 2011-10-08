package edu.luc.cs.laufer.cs473.shapealgebra

/**
 * The category of shape algebras extended to support additional shapes.
 */
import java.awt.Color
import java.awt.Point

trait ExtendedShapeAlgebra[R] extends ShapeAlgebra[R] {

  def visitStroke(r:R, s:Stroke):R
  def visitFill(f:Shape):R
  def vivitOutline(s:Shape):R
  def visitPolygon(p: Polygon): R
  def visitRotate(int:Int,s:Shape):R
  def visitCircle(c:Circle): R
  
  // TODO: add missing visit methods similarly to Location

  /**
   * The extended catamorphism for shapes.
   */
  override def fold(s: Shape): R = s match {
    case p: Polygon => visitPolygon(p)
    // TODO: add missing cases similarly to Location
    case _ => super.fold(s)
  }
}
