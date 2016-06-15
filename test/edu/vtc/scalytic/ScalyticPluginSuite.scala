//-----------------------------------------------------------------------
// FILE    : ScalyticPluginSuite.scala
// SUBJECT :
// AUTHOR  : (C) Copyright 2012 by Peter C. Chapin <PChapin@vtc.vsc.edu>
//
// This file is part of the plugin template distributed with the compiler.
//-----------------------------------------------------------------------
package edu.vtc.scalytic

import org.scalatest.Suite

class ScalyticPluginSuite extends Suite {
  def testName() {
    import scala.tools.nsc.{Global, Settings}
    import scala.tools.nsc.reporters.ConsoleReporter
    val settings = new Settings
    val compiler = new Global(settings, new ConsoleReporter(settings))
    val plugin = new ScalyticPlugin(compiler)
    expectResult("Scalytic") {
      plugin.name
    }
  }
}
