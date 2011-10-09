package edu.luc.cs.laufer.cs473.shapealgebra

object ExtendedShapeSize extends ExtendedShapeAlgebra[Int] {
  override def visitRectangle(r:Rectangle)= ShapeSize.visitRectangle(r)
  override def visitEllipse(e: Ellipse) = ShapeSize.visitEllipse(e)  
  override def visitLocation(r:Int, l:Location) = ShapeSize.visitLocation(r, l)
  override def visitGroup(rs:Seq[Int],g:Group) = ShapeSize.visitGroup(rs, g)
  override def visitStroke(r:Int, shape: Stroke) = r
  override def visitFill(r:Int,f:Fill) = r
  override def visitOutline(r:Int,o:Outline) =r
  override def visitPolygon(ps:Seq[Int],p:Polygon) = 1
  override def visitPoint(p:Point) = 0
  override def visitRotate(r:Int,s:Rotate) = r
  override def visitCircle(c:Circle) = 1
}


object ExtendedShapeDepth extends ExtendedShapeAlgebra[Int] {
  override def visitRectangle(r:Rectangle)= ShapeSize.visitRectangle(r)
  override def visitEllipse(e: Ellipse) = ShapeSize.visitEllipse(e)
  override def visitLocation(r:Int, l:Location)= ShapeSize.visitLocation(r, l)+1
//  override def visitGroup(rs:Seq[Int],g:Group)= ShapeSize.visitGroup(rs, g)
  override def visitGroup(rs:Seq[Int],g:Group)= g.shapes.map(z => ExtendedShapeDepth(z)).max +1
  override def visitStroke(r:Int,s:Stroke)=ExtendedShapeSize.visitStroke(r,s)+1
  override def visitFill(r:Int,f:Fill)=ExtendedShapeSize.visitFill(r,f)+1
  override def visitOutline(r:Int,o:Outline)=ExtendedShapeSize.visitOutline(r,o)+1
  override def visitPolygon(ps:Seq[Int],p:Polygon)=ExtendedShapeSize.visitPolygon(ps:Seq[Int],p:Polygon)
  override def visitPoint(p:Point)=ExtendedShapeSize.visitPoint(p)
  override def visitRotate(r:Int,s:Rotate)=ExtendedShapeSize.visitRotate(r,s)+1
  override def visitCircle(c:Circle)= ExtendedShapeSize.visitCircle(c)
}

//class ExtendedBoundingBox extends BoundingBox with ExtendedShapeAlgebra[Location] {
//  // methods for original shapes inherited at class level
//  // TODO: methods for the other additional (extended) shapes
//  override def visitStroke(r: Location, s: Stroke) = r
//  // TODO: reduce Circle to Ellipse (avoid code duplication)
//  // etc.
//}
//
//object ExtendedBoundingBox extends ExtendedBoundingBox