import sbt._

object Dependencies {

  val catsCore = "org.typelevel" %% "cats-core" % "2.9.0"
  val catsEffect = "org.typelevel" %% "cats-effect" % "3.4.8"

  val catsAndFriends = Seq(catsCore, catsEffect)


  val weaver = "com.disneystreaming" %% "weaver-cats" % "0.8.1"
  val mockito = "org.mockito" %% "mockito-scala" % "1.17.12"
  val scalatest = "org.scalatest" %% "scalatest" % "3.2.15"
  val munit = "org.typelevel" %% "munit-cats-effect" % "2.0.0-M3"

  val testingLibraries = Seq(weaver, mockito, scalatest, munit)

  val log4cats = "org.typelevel" %% "log4cats-core" % "2.5.0"
  val log4catsSlf4j = "org.typelevel" %% "log4cats-slf4j" % "2.5.0"
  val logback = "ch.qos.logback" % "logback-classic" % "1.2.3"

  val logging = Seq(logback, log4cats, log4catsSlf4j)

}
