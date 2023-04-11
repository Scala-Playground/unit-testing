package jakeservice.services

import jakeservice.database.AccountStore
import cats.effect.IO
import cats.implicits.catsSyntaxApplicativeId

import java.util.UUID

class JakeRetrievalServiceImpl1(store: AccountStore) extends JakeRetrievalService {

  /** Pros
    *   - Fast (`O(1)` time complexity)
    *   - 99% Accurate
    */
  def isJake(id: UUID): IO[Boolean] = false.pure[IO]

}
