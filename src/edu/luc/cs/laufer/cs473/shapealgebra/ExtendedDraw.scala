package edu.luc.cs.laufer.cs473.shapealgebra

import java.awt.Graphics2D
import java.awt.Color

class ExtendedDraw extends Draw {

  override def draw(g: Graphics2D)(s: Shape): Unit = s match {
    case Stroke(color: Color, shape: Shape) =>{
      
    }
    case Fill(shape: Shape) => fill(g)(shape)
    case Outline(shape: Shape)  => {
      
    }
    case Polygon(points @_*) => {
      
    }
    //case class Polygon(shapes: Shape*) extends Shape
    //case Point(x: Int, y: Int) extends Shape
    case Rotate(theta: Int, shape: Shape) => {
      
    }
    case Circle(radius: Int) => {
      
    }
    // TODO: cases for the additional shapes
	// TODO: reduce Circle to Ellipse (avoid code duplication)
  	case _ => super.draw(g)(s)
  }

  def fill(g: Graphics2D)(s: Shape): Unit = s match {
    case Ellipse(hw, hh) => g.fillArc(-hw, -hh, 2 * hw, 2 * hh, 0, 360)
    case Rectangle(w, h) => g.fillRect(0, 0, w, h)
//    case Polygon(points @_*) => fillPolygon(Polygon p)
    case Circle(c) => g.fillArc(-c/2, -c/2, 2 * c, 2*c, 0, 360)
    case _ => draw(g)(s)
  }
}

object ExtendedDraw extends ExtendedDraw {
  def apply(g: Graphics2D) = draw(g)(_)
}