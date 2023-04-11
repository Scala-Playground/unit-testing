scalaVersion := "2.13.10"
name := "testing-different-frameworks"
libraryDependencies ++= Seq(
  "com.disneystreaming" %% "weaver-cats" % "0.8.1" % Test,
  "org.mockito" %% "mockito-scala" % "1.17.12" % Test,
  "org.scalatest" %% "scalatest" % "3.2.15" % Test,
  "org.typelevel" %% "munit-cats-effect" % "2.0.0-M3" % Test,
  "org.typelevel" %% "cats-core" % "2.9.0",
  "org.typelevel" %% "cats-effect" % "3.4.8"
)
testFrameworks += new TestFramework("weaver.framework.CatsEffect")
