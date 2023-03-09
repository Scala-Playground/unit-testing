package _3.simpleIOapp.database

import _3.simpleIOapp.model.Account
import cats.effect.IO

import java.util.UUID

trait AccountStore {

  def get(id: UUID): IO[Option[Account]]

  def create(account: Account): IO[Int]

  def updateName(id: UUID, name: String): IO[Int]

}
