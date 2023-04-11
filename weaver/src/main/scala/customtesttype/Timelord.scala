package customtesttype

import java.time._
import java.time.format.{DateTimeFormatter, DateTimeFormatterBuilder}
import scala.concurrent.duration.FiniteDuration
import scala.util.Try

object Timelord {

  private val filenameTimestampFormat = "yyyyMMddHHmmss"
  private val contentsTimestampFormat = "yyyy-MM-dd HH:mm"
  private val contentsDateFormat = "yyyy-MM-dd"
  val cstZoneId: ZoneId = ZoneId.of("America/Chicago")

  private val filenameTimestampFormatter =
    DateTimeFormatter
      .ofPattern(filenameTimestampFormat)
      .withZone(cstZoneId)

  private val contentsTimestampFormatter =
    DateTimeFormatter
      .ofPattern(contentsTimestampFormat)
      .withZone(cstZoneId)

  private val contentsDateFormatter =
    DateTimeFormatter
      .ofPattern(contentsDateFormat)
      .withZone(cstZoneId)

  private val instantParser =
    new DateTimeFormatterBuilder()
      .appendOptional(DateTimeFormatter.ISO_DATE_TIME)
      .appendOptional(DateTimeFormatter.ISO_INSTANT)
      .toFormatter()

  def formatTimestampForContent(time: LocalDateTime): String =
    time.format(contentsTimestampFormatter)

  def formatDateForContent(startTime: LocalDateTime): String =
    startTime.format(contentsDateFormatter)

  def convertUTCTimestampToCSTDate(timestamp: String): String =
    formatDateForContent(
      Try(ZonedDateTime.parse(timestamp, instantParser))
        .getOrElse(LocalDateTime.parse(timestamp, instantParser).atZone(ZoneOffset.UTC))
        .withZoneSameInstant(cstZoneId)
        .toLocalDateTime
    )

  def truncateTimestampToDateString(timestamp: String): String =
    formatDateForContent(
      Try(ZonedDateTime.parse(timestamp, instantParser))
        .getOrElse(LocalDateTime.parse(timestamp, instantParser).atZone(ZoneOffset.UTC))
        .toLocalDateTime
    )

  def formatTimestampForFilename(startTime: LocalDateTime): String =
    startTime.format(filenameTimestampFormatter)

  def finiteDurationMillisToTimestamp(finiteDuration: FiniteDuration): String =
    DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(
      ZonedDateTime.ofInstant(Instant.ofEpochMilli(finiteDuration.toMillis), ZoneId.of("UTC"))
    )

}
