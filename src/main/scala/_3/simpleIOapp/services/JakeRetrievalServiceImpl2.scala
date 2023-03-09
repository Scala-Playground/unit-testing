package _3.simpleIOapp.services

import _3.simpleIOapp.database.AccountStore
import cats.effect.IO
import cats.implicits.catsSyntaxApplicativeId
import org.typelevel.log4cats.StructuredLogger
import org.typelevel.log4cats.slf4j.Slf4jLogger

import java.util.UUID

class JakeRetrievalServiceImpl2(store: AccountStore) extends JakeRetrievalService {
  lazy val slog: StructuredLogger[IO] = Slf4jLogger.getLogger[IO]

  def isJake(id: UUID): IO[Boolean] = {
    val log = slog.addContext(Map("accountId" -> id.toString))

    for {
      _ <- log.info("isJake called")
      maybeAccount <- store.get(id)
      maybeIsJake = maybeAccount.map(acct => acct.name.toLowerCase == "jake")
      isJake <-
        maybeIsJake
          .fold(log.error("Account not found").as(false))(_.pure[IO])
      _ <- if (isJake) log.info("Account was jake") else log.info("Account was not jake")
    } yield isJake
  }

}
