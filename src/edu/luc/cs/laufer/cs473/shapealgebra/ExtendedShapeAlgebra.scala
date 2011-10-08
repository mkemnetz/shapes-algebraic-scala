package edu.luc.cs.laufer.cs473.shapealgebra

/**
 * The category of shape algebras extended to support additional shapes.
 */
import java.awt.Color
import java.awt.Point

trait ExtendedShapeAlgebra[R] extends ShapeAlgebra[R] {

  def visitStroke(r: R, s: Stroke):R
  def visitFill(r: R, f: Shape):R
  def visitOutline(s: Shape):R
  def visitPolygon(p: Polygon): R
  def visitPoint(p: Point): R
  def visitRotate(int: Int,s: Shape):R
  def visitCircle(c: Circle): R

  /**
   * The extended catamorphism for shapes.
   */
  override def fold(s: Shape): R = s match {
   	
    case stroke: Stroke => visitStroke(fold(stroke.shape),stroke)
    case point: Point => visitPoint(point)
    case fill: Fill => visitFill(fold(fill.shape),fill)
    case outline: Outline=>visitOutline(outline)
    case polygon: Polygon => visitPolygon(polygon)
    case rotate: Rotate=>visitRotate(rotate.theta,rotate)
    case circle: Circle=>visitCircle(circle)
    
    case _ => super.fold(s)
  }
}
