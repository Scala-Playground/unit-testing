package customtesttype

import cats.effect.IO
import utils.Fakes
import weaver.Expectations
import weaver.Expectations.Helpers.{expect, forEach}

import java.time.LocalDateTime

/** Test cases for date formatters (i.e. `LocalDateTime => String`)
  *
  * @param expected
  *   The string that the `LocalDateTime`(s) should map to
  * @param times
  *   The `LocalDateTime`s being tested
  * @note
  *   `LocalDateTime`s contain multiple independent dimensions (hours, minutes, seconds, etc...). A
  *   formatter might not use some of these dimensions (i.e. it might be invariant with respect to
  *   these dimensions), so there are convenience functions that will take a test case and return
  *   additional tests that have randomized values along the dimension that the formatter is
  *   invariant in.
  */
sealed trait TestCase extends Fakes {

  /** Creates a spread of tests from a single testcase with randomized values that span one
    * dimension
    *
    * @param transformation
    *   setter function for LocalDateTime dimension to be spanned
    */
  def invariantWithRespectTo(transformation: LocalDateTime => Int => LocalDateTime): AllMatch

  final def invariantWRTNanoSeconds: AllMatch =
    invariantWithRespectTo(time => i => time.withNano(i.abs % 1000000000))

  final def invariantWRTSeconds: AllMatch =
    invariantWithRespectTo(time => i => time.withSecond(i.abs % 60))

  final def invariantWRTMinutes: AllMatch =
    invariantWithRespectTo(time => i => time.withMinute(i.abs % 60))

  final def invariantWRTHours: AllMatch =
    invariantWithRespectTo(time => i => time.withHour(i.abs % 24))
}

case class ExactMatch(time: LocalDateTime, expected: String) extends TestCase {
  override def invariantWithRespectTo(
    transformation: LocalDateTime => Int => LocalDateTime): AllMatch =
    AllMatch(
      List.empty
        .padTo(10, {}) // Choice of 10 generated test variants is arbitrary
        .map(_ => transformation(time)(int())),
      expected
    )
}
case class AllMatch(times: List[LocalDateTime], expected: String) extends TestCase {
  override def invariantWithRespectTo(
    transformation: LocalDateTime => Int => LocalDateTime): AllMatch =
    AllMatch(
      times.flatMap(time =>
        List.empty
          .padTo(5, {}) // Choice is arbitrary, but we don't want it too big since it quickly increases number of tests
          .map(_ => transformation(time)(int()))
      ),
      expected
    )

}
object TestCase {
  def buildTestRunner(method: LocalDateTime => String, test: IO[TestCase]): IO[Expectations] =
    test.flatMap {
      case ExactMatch(time, expected) =>
        IO(expect.same(method(time), expected))
      case AllMatch(times, expected) =>
        IO(forEach(times) { time =>
          expect.same(method(time), expected)
        })
    }

  /** This defines a nice infix function that should make creating tests look prettier :). If it
    * looks confusing, you're not alone. Check out the official documentation for it
    * [[https://docs.scala-lang.org/overviews/core/value-classes.html#:~:text=One%20use%20case,using%20RichInt%20methods. here]]
    */
  implicit class LocalDateTimeToTestCaseOps(val a: LocalDateTime) extends AnyVal {
    def *->(string: String): TestCase = ExactMatch(a, string)
  }

}
