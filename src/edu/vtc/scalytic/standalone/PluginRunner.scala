//-----------------------------------------------------------------------
// FILE    : PluginRunner.scala
// SUBJECT :
// AUTHOR  : (C) Copyright 2012 by Peter C. Chapin <PChapin@vtc.vsc.edu>
//
// This file is part of the plugin template distributed with the compiler.
//-----------------------------------------------------------------------
package edu.vtc.scalytic.standalone

import edu.vtc.scalytic.{ScalyticAnnotationChecker, ScalyticPlugin}
import scala.tools.nsc.{Global, Settings, SubComponent}
import scala.tools.nsc.reporters.{ConsoleReporter, Reporter}

/**
 * This class is a compiler that will be used for running the plugin in standalone mode.
 */
class PluginRunner(settings: Settings, reporter: Reporter) extends Global(settings, reporter) {
  def this(settings: Settings) = this(settings, new ConsoleReporter(settings))

  val annotationChecker = new ScalyticAnnotationChecker {
    val global: PluginRunner.this.type = PluginRunner.this
  }
  addAnnotationChecker(annotationChecker.checker)

  /**
   * The phases to be run.
   *
   *  @todo Adapt to specific plugin implementation.
   */
  override protected def computeInternalPhases() {
    phasesSet += syntaxAnalyzer
    phasesSet += analyzer.namerFactory
    phasesSet += analyzer.typerFactory
    // phasesSet += superAccessors        // Add super accessors.
    // phasesSet += pickler	             // Serialize symbol tables.
    // phasesSet += refchecks			       // Perform reference and override checking, translate nested objects.

    for (phase <- ScalyticPlugin.components(this)) {
      phasesSet += phase
    }
  }

}
