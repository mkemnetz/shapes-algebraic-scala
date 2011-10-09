package edu.luc.cs.laufer.cs473.shapealgebra

/**
 * The category of shape algebras extended to support additional shapes.
 */
import java.awt.Color


trait ExtendedShapeAlgebra[R] extends ShapeAlgebra[R] {

  def visitStroke(r: R, s: Stroke):R
  def visitFill(r: R, f: Fill):R
  def visitOutline(r: R, o: Outline):R
  def visitPolygon(rs: Seq[R], p: Polygon): R
  def visitPoint(p: Point): R
  def visitRotate(r: R, ro: Rotate):R
  def visitCircle(c: Circle): R

  /**
   * The extended catamorphism for shapes.
   */
  override def fold(s: Shape): R = s match {
   	
    case stroke: Stroke => visitStroke(fold(stroke.shape),stroke)
    case point: Point => visitPoint(point)
    case fill: Fill => visitFill(fold(fill.shape),fill)
    case outline: Outline => visitOutline(fold(outline.shape),outline)
    case polygon: Polygon => visitPolygon(polygon.points.map(fold(_)), polygon)
    case rotate: Rotate => visitRotate(fold(rotate.s), rotate)
    case circle: Circle => visitCircle(circle)
    
    case _ => super.fold(s)
  }
}
