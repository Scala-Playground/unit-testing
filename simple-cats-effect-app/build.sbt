ThisBuild / scalaVersion := "2.13.10"
name := "simple-cats-effect-app"
libraryDependencies ++= Seq(
  "com.disneystreaming" %% "weaver-cats" % "0.8.1" % Test,
  "org.mockito" %% "mockito-scala" % "1.17.12" % Test,
  "org.typelevel" %% "cats-core" % "2.9.0",
  "org.typelevel" %% "cats-effect" % "3.4.8",
  "org.typelevel" %% "log4cats-core" % "2.5.0",
  "org.typelevel" %% "log4cats-slf4j" % "2.5.0",
  "ch.qos.logback" % "logback-classic" % "1.2.3"
)
testFrameworks += new TestFramework("weaver.framework.CatsEffect")
