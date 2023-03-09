package utils

import cats.effect.IO
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.when
import org.mockito.MockitoSugar.mock
import org.typelevel.log4cats.StructuredLogger
import weaver.Log

object LoggerUtils {

  def makeMock: StructuredLogger[IO] = {
    val loggerMock: StructuredLogger[IO] = mock[StructuredLogger[IO]]
    when(loggerMock.info(any[String])).thenReturn(IO.unit)
    when(loggerMock.info(any[Map[String, String]])(any[String])).thenReturn(IO.unit)
    when(loggerMock.info(any[Throwable])(any[String])).thenReturn(IO.unit)

    when(loggerMock.warn(any[String])).thenReturn(IO.unit)
    when(loggerMock.warn(any[Throwable])(any[String])).thenReturn(IO.unit)

    when(loggerMock.error(any[Map[String, String]], any[Throwable])(any[String]))
      .thenReturn(IO.unit)
    when(loggerMock.error(any[Throwable])(any[String])).thenReturn(IO.unit)
    when(loggerMock.error(any[String])).thenReturn(IO.unit)

    when(loggerMock.addContext(any[Map[String, String]])).thenReturn(loggerMock)
    when(loggerMock.mapK(any)).thenCallRealMethod(): @annotation.nowarn
    loggerMock
  }

  def fromWeaverLog[F[_]](log: Log[F]): StructuredLogger[F] =
    new StructuredLogger[F] {
      override def trace(ctx: Map[String, String])(msg: => String): F[Unit] =
        log.debug(msg, ctx)

      override def trace(ctx: Map[String, String], t: Throwable)(msg: => String): F[Unit] =
        log.debug(msg, ctx, t)

      override def debug(ctx: Map[String, String])(msg: => String): F[Unit] =
        log.debug(msg, ctx)

      override def debug(ctx: Map[String, String], t: Throwable)(msg: => String): F[Unit] =
        log.debug(msg, ctx, t)

      override def info(ctx: Map[String, String])(msg: => String): F[Unit] =
        log.info(msg, ctx)

      override def info(ctx: Map[String, String], t: Throwable)(msg: => String): F[Unit] =
        log.info(msg, ctx, t)

      override def warn(ctx: Map[String, String])(msg: => String): F[Unit] =
        log.warn(msg, ctx)

      override def warn(ctx: Map[String, String], t: Throwable)(msg: => String): F[Unit] =
        log.warn(msg, ctx, t)

      override def error(ctx: Map[String, String])(msg: => String): F[Unit] =
        log.error(msg, ctx)

      override def error(ctx: Map[String, String], t: Throwable)(msg: => String): F[Unit] =
        log.error(msg, ctx, t)

      override def error(message: => String): F[Unit] =
        log.error(message)

      override def warn(message: => String): F[Unit] =
        log.warn(message)

      override def info(message: => String): F[Unit] =
        log.info(message)

      override def debug(message: => String): F[Unit] =
        log.debug(message)

      override def trace(message: => String): F[Unit] =
        log.debug(message)

      override def error(t: Throwable)(message: => String): F[Unit] =
        log.error(message, cause = t)

      override def warn(t: Throwable)(message: => String): F[Unit] =
        log.warn(message, cause = t)

      override def info(t: Throwable)(message: => String): F[Unit] =
        log.info(message, cause = t)

      override def debug(t: Throwable)(message: => String): F[Unit] =
        log.info(message, cause = t)

      override def trace(t: Throwable)(message: => String): F[Unit] =
        log.debug(message, cause = t)
    }

}
