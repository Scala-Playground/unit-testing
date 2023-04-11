ThisBuild / scalaVersion := "2.13.10"
name := "weaver"
libraryDependencies ++= Seq(
  "com.disneystreaming" %% "weaver-cats" % "0.8.1" % Test,
  "org.typelevel" %% "cats-core" % "2.9.0",
  "org.typelevel" %% "cats-effect" % "3.4.8"
)
testFrameworks += new TestFramework("weaver.framework.CatsEffect")
