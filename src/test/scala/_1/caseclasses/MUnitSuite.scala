package _1.caseclasses

import munit.CatsEffectSuite

import java.util.UUID

class MUnitSuite extends CatsEffectSuite {

  test("basic equals") {
    val account1 = Account("jake", "jake@jake.jake", UUID.randomUUID())
    val account2 = account1.copy(accountId = UUID.randomUUID())

    assert(account1 == account2)
  }

  test("use same instead") {
    val account1 = Account("jake", "jake@jake.jake", UUID.randomUUID())
    val account2 = account1.copy(accountId = UUID.randomUUID())

    assertEquals(account1, account2)
  }

  test("see how nicely it points it out?") {
    val account1 = Account("jake", "jake@jake.jake", UUID.randomUUID())
    val account2 = account1.copy(email = "jake@jake.com")

    assertEquals(account1, account2)
  }

  test("this shouldn't succeed right?") {
    val account1 = Account("jake", "jake@jake.jake", UUID.randomUUID())
    val account2 = account1.copy(email = "fred@fred.jake")

    assertEquals(account1, account2) // not ignored
    assertEquals(account1, account1)
  } // this is cats land but there's still some unit test "magic" going on here

  test("okay now this one fails as expected") {
    val account1 = Account("jake", "jake@jake.jake", UUID.randomUUID())
    val account2 = account1.copy(email = "fred@fred.jake")

    assertEquals(account1, account2)
    assertEquals(account1, account1)
  }

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

    assertEquals(bigClass, bigClass2)
  }

}
