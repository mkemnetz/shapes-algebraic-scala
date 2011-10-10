package edu.luc.cs.laufer.cs473.shapealgebra

import java.awt.Graphics2D
import java.awt.Color

class ExtendedDraw extends Draw {

  override def draw(g: Graphics2D)(s: Shape): Unit = s match {
    case Stroke(color: Color, shape: Shape) =>{
      g.setColor(color)
      draw(g)(shape)
      g.setColor(new Color(255, 255, 255))
    }
    case Fill(shape: Shape) => fill(g)(shape)
//    case Outline(shape: Shape)  => {
//      
//    }
//    case Polygon(points @_*) => {
//      
//    }
    case Rotate(theta: Int, shape: Shape) => {
    	g.rotate(math.toRadians(theta))
    	draw(g)(shape)
    	g.rotate(math.toRadians(-theta))
    }
    case Circle(radius: Int) => {
      println(g.getColor())
    	draw(g)(Ellipse(radius,radius))
    }
    // TODO: cases for the additional shapes
	// TODO: reduce Circle to Ellipse (avoid code duplication)
  	case _ => super.draw(g)(s)
  }

  def fill(g: Graphics2D)(s: Shape): Unit = s match {
    case Ellipse(hw, hh) => g.fillArc(-hw, -hh, 2 * hw, 2 * hh, 0, 360)
    case Rectangle(w, h) => g.fillRect(0, 0, w, h)
//    case Polygon(points @_*) => fillPolygon(Polygon p)
    case Circle(c) => g.fillArc(-c, -c, 2 * c, 2*c, 0, 360)
    case _ => draw(g)(s)
  }
}

object ExtendedDraw extends ExtendedDraw {
  def apply(g: Graphics2D) = draw(g)(_)
}