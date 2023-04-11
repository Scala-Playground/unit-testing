package customtesttype

import TestCase.LocalDateTimeToTestCaseOps
import cats.effect.IO
import weaver.SimpleIOSuite

import java.time.LocalDateTime
import java.util.concurrent.TimeUnit
import scala.concurrent.duration.FiniteDuration

object TimelordSpec extends SimpleIOSuite {

  List(
    "format a datetime" ->
      LocalDateTime.of(2000, 2, 7, 19, 45) *-> "20000207194500",
    "format a datetime before epoch time" ->
      LocalDateTime.of(1950, 1, 1, 12, 30) *-> "19500101123000",
    "format epoch datetime" ->
      LocalDateTime.of(1960, 1, 1, 0, 0) *-> "19600101000000",
    "format a future date" ->
      LocalDateTime.of(2050, 8, 20, 23, 59) *-> "20500820235900"
  ).foreach(testFormatTimestampForFilename)

  List(
    "format a datetime" ->
      LocalDateTime.of(2000, 2, 7, 19, 45) *-> "2000-02-07 19:45",
    "format a datetime before epoch time" ->
      LocalDateTime.of(1950, 1, 1, 12, 30) *-> "1950-01-01 12:30",
    "format epoch datetime" ->
      LocalDateTime.of(1960, 1, 1, 0, 0) *-> "1960-01-01 00:00",
    "format a future date" ->
      LocalDateTime.of(2050, 8, 20, 23, 59) *-> "2050-08-20 23:59"
  ).foreach(testFormatTimestampForContent)

  List(
    "format a datetime" ->
      LocalDateTime.of(2000, 2, 7, 19, 45) *-> "2000-02-07",
    "format a datetime before epoch time" ->
      LocalDateTime.of(1950, 1, 1, 12, 30) *-> "1950-01-01",
    "format epoch datetime" ->
      LocalDateTime.of(1960, 1, 1, 0, 0) *-> "1960-01-01",
    "format a future date" ->
      LocalDateTime.of(2050, 8, 20, 23, 59) *-> "2050-08-20"
  ).foreach(testFormatDateForContent)

  List(
    "format a millis duration" ->
      FiniteDuration(10000L * 24 * 60 * 60 * 1000 + 2345,
                     TimeUnit.MILLISECONDS
      ) -> "1997-05-19T00:00:02.345Z",
    "format a day duration" -> FiniteDuration(10000, TimeUnit.DAYS) -> "1997-05-19T00:00:00Z"
  ).foreach(testDurationToTimestamp)

  // precision test
  List(
    ("2021-09-28T15:46:27Z", "2021-09-28"),
    ("2021-09-28T15:46:27.1Z", "2021-09-28"),
    ("2021-09-28T15:46:27.12Z", "2021-09-28"),
    ("2021-09-28T15:46:27.123Z", "2021-09-28"),
    ("2021-09-28T15:46:27.1234Z", "2021-09-28"),
    ("2021-09-28T15:46:27.12345Z", "2021-09-28"),
    ("2021-09-28T15:46:27.123456Z", "2021-09-28"),
    ("2021-09-28T15:46:27.1234567Z", "2021-09-28"),
    ("2021-09-28T15:46:27.12345678Z", "2021-09-28"),
    ("2021-09-28T15:46:27.123456789Z", "2021-09-28"),
    ("2021-09-28T15:46:27", "2021-09-28"),
    ("2021-09-28T15:46:27.1", "2021-09-28"),
    ("2021-09-28T15:46:27.12", "2021-09-28"),
    ("2021-09-28T15:46:27.123", "2021-09-28"),
    ("2021-09-28T15:46:27.1234", "2021-09-28"),
    ("2021-09-28T15:46:27.12345", "2021-09-28"),
    ("2021-09-28T15:46:27.123456", "2021-09-28"),
    ("2021-09-28T15:46:27.1234567", "2021-09-28"),
    ("2021-09-28T15:46:27.12345678", "2021-09-28"),
    ("2021-09-28T15:46:27.123456789", "2021-09-28")
  ).foreach { case (time, result) =>
    pureTest(s"convertUTCTimestampToCSTDate should parse $time into $result") {
      expect.same(Timelord.convertUTCTimestampToCSTDate(time), result)
    }
  }

  // hourly test
  List(
    ("2021-09-28T00:46:27", "2021-09-27"),
    ("2021-09-28T01:46:27", "2021-09-27"),
    ("2021-09-28T02:46:27", "2021-09-27"),
    ("2021-09-28T03:46:27", "2021-09-27"),
    ("2021-09-28T04:46:27", "2021-09-27"),
    ("2021-09-28T05:00:00", "2021-09-28"),
    ("2021-09-28T05:46:27", "2021-09-28"),
    ("2021-09-28T06:46:27", "2021-09-28"),
    ("2021-09-28T07:46:27", "2021-09-28"),
    ("2021-09-28T08:46:27", "2021-09-28"),
    ("2021-09-28T09:46:27", "2021-09-28"),
    ("2021-09-28T10:46:27", "2021-09-28"),
    ("2021-09-28T11:46:27", "2021-09-28"),
    ("2021-09-28T12:46:27", "2021-09-28"),
    ("2021-09-28T13:46:27", "2021-09-28"),
    ("2021-09-28T14:46:27", "2021-09-28"),
    ("2021-09-28T15:46:27", "2021-09-28"),
    ("2021-09-28T16:46:27", "2021-09-28"),
    ("2021-09-28T17:46:27", "2021-09-28"),
    ("2021-09-28T18:46:27", "2021-09-28"),
    ("2021-09-28T19:46:27", "2021-09-28"),
    ("2021-09-28T20:46:27", "2021-09-28"),
    ("2021-09-28T21:46:27", "2021-09-28"),
    ("2021-09-28T22:46:27", "2021-09-28"),
    ("2021-09-28T23:46:27", "2021-09-28")
  ).foreach { case (time, result) =>
    pureTest(s"convertUTCTimestampToCSTDate should convert $time to $result") {
      expect.same(Timelord.convertUTCTimestampToCSTDate(time), result)
    }
  }

  // precision test
  List(
    ("2021-09-28T15:46:27Z", "2021-09-28"),
    ("2021-09-28T15:46:27.1Z", "2021-09-28"),
    ("2021-09-28T15:46:27.12Z", "2021-09-28"),
    ("2021-09-28T15:46:27.123Z", "2021-09-28"),
    ("2021-09-28T15:46:27.1234Z", "2021-09-28"),
    ("2021-09-28T15:46:27.12345Z", "2021-09-28"),
    ("2021-09-28T15:46:27.123456Z", "2021-09-28"),
    ("2021-09-28T15:46:27.1234567Z", "2021-09-28"),
    ("2021-09-28T15:46:27.12345678Z", "2021-09-28"),
    ("2021-09-28T15:46:27.123456789Z", "2021-09-28"),
    ("2021-09-28T02:46:27.123456789Z", "2021-09-28"),
    ("2021-09-28T15:46:27", "2021-09-28"),
    ("2021-09-28T15:46:27.1", "2021-09-28"),
    ("2021-09-28T15:46:27.12", "2021-09-28"),
    ("2021-09-28T15:46:27.123", "2021-09-28"),
    ("2021-09-28T15:46:27.1234", "2021-09-28"),
    ("2021-09-28T15:46:27.12345", "2021-09-28"),
    ("2021-09-28T15:46:27.123456", "2021-09-28"),
    ("2021-09-28T15:46:27.1234567", "2021-09-28"),
    ("2021-09-28T15:46:27.12345678", "2021-09-28"),
    ("2021-09-28T15:46:27.123456789", "2021-09-28"),
    ("2021-09-28T02:46:27.123456789", "2021-09-28"),
    ("2021-09-28T00:00:00.0000", "2021-09-28"),
    ("2021-09-28T07:00:00", "2021-09-28"),
    ("2022-12-15T12:00:00", "2022-12-15")
  ).foreach { case (time, result) =>
    pureTest(s"truncateTimestampToDateString should parse $time into $result") {
      expect.same(Timelord.truncateTimestampToDateString(time), result)
    }
  }

  // hourly test
  List(
    ("2021-09-28T00:00:00", "2021-09-28"),
    ("2021-09-28T01:00:00", "2021-09-28"),
    ("2021-09-28T02:00:00", "2021-09-28"),
    ("2021-09-28T03:00:00", "2021-09-28"),
    ("2021-09-28T04:00:00", "2021-09-28"),
    ("2021-09-28T05:00:00", "2021-09-28"),
    ("2021-09-28T06:00:00", "2021-09-28"),
    ("2021-09-28T07:00:00", "2021-09-28"),
    ("2021-09-28T08:00:00", "2021-09-28"),
    ("2021-09-28T09:00:00", "2021-09-28"),
    ("2021-09-28T10:00:00", "2021-09-28"),
    ("2021-09-28T11:00:00", "2021-09-28"),
    ("2021-09-28T12:00:00", "2021-09-28"),
    ("2021-09-28T13:00:00", "2021-09-28"),
    ("2021-09-28T14:00:00", "2021-09-28"),
    ("2021-09-28T15:00:00", "2021-09-28"),
    ("2021-09-28T16:00:00", "2021-09-28"),
    ("2021-09-28T17:00:00", "2021-09-28"),
    ("2021-09-28T18:00:00", "2021-09-28"),
    ("2021-09-28T19:00:00", "2021-09-28"),
    ("2021-09-28T20:00:00", "2021-09-28"),
    ("2021-09-28T21:00:00", "2021-09-28"),
    ("2021-09-28T22:00:00", "2021-09-28"),
    ("2021-09-28T23:00:00", "2021-09-28")
  ).foreach { case (time, result) =>
    pureTest(s"truncateTimestampToDateString should convert $time to $result") {
      expect.same(Timelord.truncateTimestampToDateString(time), result)
    }
  }

  def testFormatTimestampForFilename(tc: (String, TestCase)): Unit = {
    val (str, testCase) = tc

    test(s"formatTimestampForFilename $str")(
      TestCase.buildTestRunner(
        Timelord.formatTimestampForFilename,
        IO(testCase.invariantWRTNanoSeconds)
      )
    )
  }

  def testFormatTimestampForContent(tc: (String, TestCase)): Unit = {
    val (str, testCase) = tc

    test(s"formatTimestampForContent $str")(
      TestCase.buildTestRunner(
        Timelord.formatTimestampForContent,
        IO(testCase.invariantWRTNanoSeconds.invariantWRTSeconds)
      )
    )
  }

  def testFormatDateForContent(tc: (String, TestCase)): Unit = {
    val (str, testCase) = tc
    test(s"formatDateForContent $str")(
      TestCase.buildTestRunner(
        Timelord.formatDateForContent,
        IO(
          testCase.invariantWRTNanoSeconds.invariantWRTSeconds.invariantWRTMinutes.invariantWRTHours
        )
      )
    )
  }

  def testDurationToTimestamp(tc: ((String, FiniteDuration), String)): Unit = {
    val ((str, dur), expct) = tc
    pureTest(s"finiteDurationMillisToTimestamp $str") {
      expect.same(Timelord.finiteDurationMillisToTimestamp(dur), expct)
    }
  }

}
