//-----------------------------------------------------------------------
// FILE    : PluginPropertiesSuite.scala
// SUBJECT :
// AUTHOR  : (C) Copyright 2012 by Peter C. Chapin <PChapin@vtc.vsc.edu>
//
// This file is part of the plugin template distributed with the compiler.
//-----------------------------------------------------------------------
package edu.vtc.scalytic

import org.scalatest.Suite

class PluginPropertiesSuite extends Suite {
  def testProperties() {
    expectResult("Scala Analysis Tool") {
      PluginProperties.pluginDescription
    }
    expectResult("0.1") {
      PluginProperties.versionString
    }
  }
}
