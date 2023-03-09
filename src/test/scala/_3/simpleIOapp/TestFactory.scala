package _3.simpleIOapp

import _3.simpleIOapp.model.Account
import cats.implicits.catsSyntaxOptionId
import utils.Fakes

import java.util.UUID

trait TestFactory extends Fakes {

  def createAccount(
                     id: Option[UUID] = uuid().some,
                     name: Option[String] = string().some,
                     email: Option[String] = string().some
                   ): Account = Account(id.getOrElse(uuid()), name.getOrElse(string()), email.getOrElse(string()))

}
