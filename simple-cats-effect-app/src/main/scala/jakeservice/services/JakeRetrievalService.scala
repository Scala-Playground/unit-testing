package jakeservice.services

import cats.effect.IO

import java.util.UUID

trait JakeRetrievalService {
  def isJake(id: UUID): IO[Boolean]
}
