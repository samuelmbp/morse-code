ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.6"

lazy val root = (project in file("."))
  .settings(
    name := "morse-code",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.18" % Test
  )
