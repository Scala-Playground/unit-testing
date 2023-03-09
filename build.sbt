import Dependencies._

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "testing-playground",
    version := "0.1.0-SNAPSHOT",
    libraryDependencies ++= catsAndFriends ++ testingLibraries.map(_ % Test) ++ logging,
    testFrameworks += new TestFramework("weaver.framework.CatsEffect")
  )
