ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "testing-playground"
  )
  .aggregate(`comparing-frameworks`, weaver, `simple-cats-effect-app`)

lazy val `comparing-frameworks` = project in file("comparing-frameworks")

lazy val weaver = project in file("weaver")

lazy val `simple-cats-effect-app` = project in file("simple-cats-effect-app")
