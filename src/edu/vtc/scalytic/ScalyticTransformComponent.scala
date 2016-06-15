//-----------------------------------------------------------------------
// FILE    : ScalyticTransformComponent.scala
// SUBJECT :
// AUTHOR  : (C) Copyright 2012 by Peter C. Chapin <PChapin@vtc.vsc.edu>
//
// This file is part of the plugin template distributed with the compiler.
//-----------------------------------------------------------------------
package edu.vtc.scalytic

import scala.tools.nsc._
import scala.tools.nsc.plugins.PluginComponent
import scala.tools.nsc.transform.Transform
// import scala.tools.nsc.transform.TypingTransformers

/**
 * This class implements a plugin component using tree transformers. If a <code>Typer</code> is needed during
 * transformation, the component should mix in <code>TypingTransformers</code>. This provides a local variable
 * <code>localTyper: Typer</code> that is always updated to the current context.
 *
 *  @todo Adapt the name of this class to the plugin, and implement it.
 */
class ScalyticTransformComponent(val global: Global) extends PluginComponent
                                                     // with TypingTransformers
                                                     with Transform {
  import global._
  import global.definitions._

  val runsAfter = List[String]("refchecks")
  /**
   * The phase name of the compiler plugin
   *
   * @todo Adapt to specific plugin.
   */
  val phaseName = "scalytictransform"

  def newTransformer(unit: CompilationUnit) = new ScalyticTransformer

  /**
   * The tree transformer that implements the behavior of this component. Change the superclass to
   * <code>TypingTransformer</code> to make a local type checker <code>localTyper</code> available.
   *
   *  @todo Implement.
   */
  class ScalyticTransformer extends /*Typing*/ Transformer {
    /**
     * When using <code>preTransform</code>, each node is visited before its children.
     */
    def preTransform(tree: Tree): Tree = tree match {
      case _ => tree
    }

    /**
     * When using <code>postTransform</code>, each node is visited after its children.
     */
    def postTransform(tree: Tree): Tree = tree match {
      case New(tpt) =>
          println("post-transforming new "+ tpt)
          tree
      case _ => tree
    }

    override def transform(tree: Tree): Tree = {
      postTransform(super.transform(preTransform(tree)))
    }
  }
}
