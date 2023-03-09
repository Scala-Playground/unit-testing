package _1.caseclasses

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import java.util.UUID

/** An example of the `Suite` pattern written in scalatest.
  */
class ScalaTestSuite extends AnyFunSuite with Matchers {

  test("basic equals") {
    val account1 = Account("jake", "jake@jake.jake", UUID.randomUUID())
    val account2 = account1.copy(accountId = UUID.randomUUID())

    assert(account1 == account2)
  }

  test("use assertResult instead") {
    val account1 = Account("jake", "jake@jake.jake", UUID.randomUUID())
    val account2 = account1.copy(accountId = UUID.randomUUID())

    assertResult(account1)(account2)
  }

  test("use shouldBe instead") {
    val account1 = Account("jake", "jake@jake.jake", UUID.randomUUID())
    val account2 = account1.copy(email = "jake@jake.com")

    account1 shouldBe account2
  }

  test("this shouldn't succeed right?") {
    val account1 = Account("jake", "jake@jake.jake", UUID.randomUUID())
    val account2 = account1.copy(email = "fred@fred.jake")

    account1 shouldBe account2
    account1 shouldBe account1
  } // this is no longer cats land

  test("lets try a test checking equality of more complex classes") {
    val account1 = Account("jake", "jake@jake.jake", UUID.randomUUID())
    val account2 = account1.copy(email = "fred@fred.jake")
    val bigClass =
      BigClass(
        List(account1, account2),
        """Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
          |incididunt ut labore et dolore magna aliqua. Praesent semper feugiat nibh sed
          |pulvinar proin gravida hendrerit lectus. Diam quis enim lobortis scelerisque.
          |Ut pharetra sit amet aliquam id. Nibh cras pulvinar mattis nunc sed blandit
          |libero volutpat. Habitasse platea dictumst vestibulum rhoncus est pellentesque
          |elit ullamcorper. Nibh sed pulvinar proin gravida. Molestie nunc non blandit
          |massa enim. Pellentesque habitant morbi tristique senectus et. Lacus sed
          |viverra tellus in hac habitasse platea dictumst vestibulum. Quis ipsum
          |suspendisse ultrices gravida dictum fusce ut. Ornare suspendisse sed nisi lacus
          |sed viverra tellus in hac. Ultrices neque ornare aenean euismod elementum nisi.
          |Ornare suspendisse sed nisi lacus sed. Adipiscing diam donec adipiscing
          |tristique risus nec feugiat in fermentum. Aenean euismod elementum nisi quis
          |eleifend quam adipiscing vitae.""".stripMargin
      )
    val bigClass2 =
      BigClass(
        List(account1, account1),
        """Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
          |incididunt ut labore et dolore magna aliqua. Praesent semper feugiat nibh sed
          |pulvinar proin gravida hendrerit lectus. Diam quis enim lobortis scelerisque.
          |Ut pharetra sit amet aliquam id. Nibh cras pulvinar mattis nunc sed blandit
          |libero volutpat. Habitasse platea dictumst vestibulum rhoncus est pellentesque
          |elit ullamcorper. Nibh sed pulvinar proin gravida. Molestie nunc non blandit
          |massa enim. Pellentesque habitant morbi tristique senectus et. Lacus sed
          |viverra tellus in hac habitasse platea dictumst vestibulum. Quis ipsum
          |suspendisse ultrices gravida dictum fuscee ut. Ornare suspendisse sed nisi lacus
          |sed viverra tellus in hac. Ultrices neque ornare aenean euismod elementum nisi.
          |Ornare suspendisse sed nisi lacus sed. Adipiscing diam donec adipiscing
          |tristique risus nec feugiat in fermentum. Aenean euismod elementum nisi quis
          |eleifend quam adipiscing vitae.""".stripMargin
      )

    assertResult(bigClass)(bigClass2)
  }

}
