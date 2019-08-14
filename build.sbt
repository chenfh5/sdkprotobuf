name := "sdkprotobuf"

version := "0.1"

scalaVersion := "2.13.0"

import sbtprotoc.ProtocPlugin.autoImport.PB

PB.targets in Compile := Seq(
  scalapb.gen() -> (sourceManaged in Compile).value
)
