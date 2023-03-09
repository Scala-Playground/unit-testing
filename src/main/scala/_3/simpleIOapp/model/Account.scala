package _3.simpleIOapp.model

import java.util.UUID

case class Account(
  accountId: UUID,
  name: String,
  email: String)
