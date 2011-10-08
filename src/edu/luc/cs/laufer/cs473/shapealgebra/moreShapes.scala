package edu.luc.cs.laufer.cs473.shapealgebra

import java.awt.Color
import java.awt.Point

case class Stroke(color: Color, shape: Shape) extends Shape
case class Fill(shape: Shape) extends Shape
case class Outline(shape: Shape) extends Shape
case class Polygon(points: Point*) extends Shape
case class Rotate(theta: Int, s: Shape) extends Shape
case class Circle(radius: Int) extends Shape