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
  override def visitGroup(rs:Seq[Int],g:Group)= g.shapes.map(z => ExtendedShapeDepth(z)).max +1
  override def visitStroke(r:Int,s:Stroke)=ExtendedShapeSize.visitStroke(r,s)+1
  override def visitFill(r:Int,f:Fill)=ExtendedShapeSize.visitFill(r,f)+1
  override def visitOutline(r:Int,o:Outline)=ExtendedShapeSize.visitOutline(r,o)+1
  override def visitPolygon(ps:Seq[Int],p:Polygon)=ExtendedShapeSize.visitPolygon(ps:Seq[Int],p:Polygon)
  override def visitPoint(p:Point)=ExtendedShapeSize.visitPoint(p)
  override def visitRotate(r:Int,s:Rotate)=ExtendedShapeSize.visitRotate(r,s)+1
  override def visitCircle(c:Circle)= ExtendedShapeSize.visitCircle(c)
}

class ExtendedBoundingBox extends BoundingBox with ExtendedShapeAlgebra[Location] {
  override def visitRectangle(r:Rectangle) = BoundingBox.visitRectangle(r)
  override def visitEllipse(e: Ellipse) = BoundingBox.visitEllipse(e)
  override def visitLocation(r:Location, l:Location) =  BoundingBox.visitLocation(r, l)
  override def visitGroup(rs:Seq[Location],g:Group) = BoundingBox.visitGroup(rs, g)
  def visitStroke(r: Location, shape: Stroke) = r
  def visitFill(r: Location,f:Fill) = r
  def visitOutline(r: Location,o:Outline) =r
  def visitPolygon(ps:Seq[Location],p:Polygon) = {
    val allX=ps.map(z => z.shape.asInstanceOf[Point].x)
    val allY=ps.map(z => z.shape.asInstanceOf[Point].y)
    val allXMin=allX.min
    val allYMin=allY.min    
    val allXMax=allX.max
    val allYMax=allY.max    
    val width=allXMax-allXMin
    val height=allYMax-allYMin
    Location(allXMin,allYMin,Rectangle(width,height))
  }
  def visitPoint(p:Point) = Location(0,0,p)
  def visitRotate(r: Location, s:Rotate) = {
    val m = ExtendedBoundingBox(s.shape)
    val llr = math.hypot(m.x, m.y)
    val lltheta = math.atan2(m.y,m.x) + math.toRadians(s.theta)
    val ulr = math.hypot(m.x, m.y+(m.shape.asInstanceOf[Rectangle].height))
    val ultheta = math.atan2(m.y+(m.shape.asInstanceOf[Rectangle].height), m.x) + math.toRadians(s.theta)
    val lrr = math.hypot(m.x+(m.shape.asInstanceOf[Rectangle].width), m.y)
    val lrtheta = math.atan2(m.y, m.x+(m.shape.asInstanceOf[Rectangle].width)) + math.toRadians(s.theta)
    val urr = math.hypot(m.x+(m.shape.asInstanceOf[Rectangle].width), m.y+(m.shape.asInstanceOf[Rectangle].height))
    val urtheta = math.atan2(m.y+(m.shape.asInstanceOf[Rectangle].height), m.x+(m.shape.asInstanceOf[Rectangle].width)) + math.toRadians(s.theta)
    val xll = llr*math.cos(lltheta)
    val yll = llr*math.sin(lltheta)
    val xul = ulr*math.cos(ultheta)
    val yul = ulr*math.sin(ultheta)
    val xlr = lrr*math.cos(lrtheta)
    val ylr = lrr*math.sin(lrtheta)
    val xur = urr*math.cos(urtheta)
    val yur = urr*math.sin(urtheta)
   
    val minX = List(xll, xul, xlr, xur).min
    val maxX = List(xll, xul, xlr, xur).max
    val minY = List(yll, yul, ylr, yur).min
    val maxY = List(yll, yul, ylr, yur).max
    val wid = (maxX-minX).abs
    val hei = (maxY-minY).abs
    new Location(minX.toInt, minY.toInt, new Rectangle(wid.toInt, hei.toInt))
  }
  def visitCircle(c:Circle) = visitEllipse(Ellipse(c.radius,c.radius))
  
}

object ExtendedBoundingBox extends ExtendedBoundingBox