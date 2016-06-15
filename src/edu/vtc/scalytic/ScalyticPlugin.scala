//-----------------------------------------------------------------------
// FILE    : ScalyticPlugin.scala
// SUBJECT :
// AUTHOR  : (C) Copyright 2012 by Peter C. Chapin <PChapin@vtc.vsc.edu>
//
// This file is part of the plugin template distributed with the compiler.
//-----------------------------------------------------------------------
package edu.vtc.scalytic

import scala.tools.nsc.Global
import scala.tools.nsc.plugins.Plugin

/**
 * A class describing the compiler plugin
 *
 *  @todo Adapt the name of this class to the plugin being implemented.
 */
class ScalyticPlugin(val global: Global) extends Plugin {

  /** The name of this plugin. Extracted from the properties file. */
  val name = PluginProperties.pluginName

  val runsAfter = List[String]("refchecks")

  /** A short description of the plugin, read from the properties file */
  val description = PluginProperties.pluginDescription
  
  /** @todo A description of the plugin's options */
  override val optionsHelp = Some(
    "  -P:"+ name +":option     sets some option for this plugin")

  /** @todo Implement parsing of plugin options */
  override def processOptions(options: List[String], error: String => Unit) {
    super.processOptions(options, error)
  }

  /**
   * The compiler components that will be applied when running this plugin.
   *
   *  @todo Adapt to the plugin being implemented.
   */
  val components = ScalyticPlugin.components(global)

  val checker = new ScalyticAnnotationChecker {
    val global: ScalyticPlugin.this.global.type = ScalyticPlugin.this.global
  }
  global.addAnnotationChecker(checker.checker)
}

object ScalyticPlugin {
  /**
   * Yields the list of Components to be executed in this plugin
   *
   *  @todo Adapt to specific implementation.
   */
  def components(global: Global) =
    List(new ScalyticComponent(global),
         new ScalyticTraverseComponent(global),
         new ScalyticTransformComponent(global),
         new ScalyticInfoTransformComponent(global))
}
