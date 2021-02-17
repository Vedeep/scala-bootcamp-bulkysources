sbtPlugin := true

ThisBuild / scalaVersion     := "2.12.13"
ThisBuild / sbtVersion       := "1.0.0"
ThisBuild / version          := "0.1.3-SNAPSHOT"
ThisBuild / organization     := "com.mysuperhomeworks"

name := "bulky-sources"

lazy val root = (project in file("."))
  .enablePlugins(SbtPlugin)
