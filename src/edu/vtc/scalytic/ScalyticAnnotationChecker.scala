//-----------------------------------------------------------------------
// FILE    : ScalyticAnnotationChecker.scala
// SUBJECT :
// AUTHOR  : (C) Copyright 2012 by Peter C. Chapin <PChapin@vtc.vsc.edu>
//
// This file is part of the plugin template distributed with the compiler.
//-----------------------------------------------------------------------
package edu.vtc.scalytic

import scala.tools.nsc.Global

abstract class ScalyticAnnotationChecker {
  val global: Global
  import global._

  object checker extends AnnotationChecker {
    def annotationsConform(tpe1: Type, tpe2: Type): Boolean = {
      println("checking: "+ tpe1 +" <: "+ tpe2)
      true
    }

    override def addAnnotations(tree: Tree, tpe: Type): Type = {
      println("adding annot to "+ tree.symbol)
      tpe
    }
  }
}
