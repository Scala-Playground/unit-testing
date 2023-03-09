package _1.caseclasses

import cats.Show
import cats.implicits.toShow
import utils.PrettyPrinting._
import weaver.SimpleIOSuite

import java.util.UUID

object WeaverSuite extends SimpleIOSuite {

  pureTest("basic equals") {
    val account1 = Account("jake", "jake@jake.jake", UUID.randomUUID())
    val account2 = account1.copy(accountId = UUID.randomUUID())

    expect(account1 == account2)
  }

  pureTest("use same instead") {
    val account1 = Account("jake", "jake@jake.jake", UUID.randomUUID())
    val account2 = account1.copy(accountId = UUID.randomUUID())

    expect.same(account1, account2)
  }

  pureTest("see how nicely it points it out?") {
    val account1 = Account("jake", "jake@jake.jake", UUID.randomUUID())
    val account2 = account1.copy(email = "jake@jake.com")

    expect.same(account1, account2)
  }

  pureTest("this shouldn't succeed right?") {
    val account1 = Account("jake", "jake@jake.jake", UUID.randomUUID())
    val account2 = account1.copy(email = "fred@fred.jake")

    expect.same(account1, account2) // ignored!!
    expect.same(account1, account1)
  } // this is cats land, where even the tests are values

  pureTest("okay now this one fails as expected") {
    val account1 = Account("jake", "jake@jake.jake", UUID.randomUUID())
    val account2 = account1.copy(email = "fred@fred.jake")

    expect.same(account1, account2) and // no longer ignored
      expect.same(account1, account1)
  }

  pureTest("lets try a test checking equality of more complex classes") {
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

    expect.same(bigClass, bigClass2)
  }

  pureTest("you can override how it prints out!") {
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

    implicit val showBigClass: Show[BigClass] =
      Show.show(bigclass =>
        "_1.caseclasses.BigClass(\n" +
          indented(bigclass.thing1.show) + ",\n" +
          indented(bigclass.thing2) +
          ")"
      )

    expect.same(bigClass, bigClass2)
  }

}
