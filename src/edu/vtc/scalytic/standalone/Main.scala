//-----------------------------------------------------------------------
// FILE    : Main.scala
// SUBJECT :
// AUTHOR  : (C) Copyright 2012 by Peter C. Chapin <PChapin@vtc.vsc.edu>
//
// This file is part of the plugin template distributed with the compiler.
//-----------------------------------------------------------------------
package edu.vtc.scalytic.standalone

import edu.vtc.scalytic.PluginProperties
import scala.tools.nsc.CompilerCommand
import scala.tools.nsc.Settings

/**
 * An object for running the plugin as standalone application.
 * 
 *  @todo print, parse and apply plugin options !!! ideally re-use the ScalyticPlugin (-> runsAfter, optionsHelp,
 *  processOptions, components, annotationChecker) instead of duplicating it here and in PluginRunner.
 */
object Main {
  def main(args: Array[String]) {
    val settings = new Settings

    val command = new CompilerCommand(args.toList, settings) {
      /**
       * The command name that will be printed in in the usage message. This is automatically set to the value of
       * 'plugin.commandname' in the file build.properties.
       */
      override val cmdName = PluginProperties.pluginCommand
    }

    if (!command.ok)
      return()

    /**
     * The version number of this plugin is read from the properties file.
     */
    if (settings.version.value) {
      println(command.cmdName + " version " + PluginProperties.versionString)
      return()
    }
    if (settings.help.value) {
      println(command.usageMsg)
      return()
    }

    val runner = new PluginRunner(settings)
    val run = new runner.Run
    run.compile(command.files)
  }
}
