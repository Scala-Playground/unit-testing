package _3.simpleIOapp

import _3.simpleIOapp.database.AccountStore
import _3.simpleIOapp.services.{JakeRetrievalService, JakeRetrievalServiceImpl1}
import cats.effect.IO
import cats.syntax.all._
import org.mockito.MockitoSugar
import weaver.SimpleIOSuite

/** This test mirrors how tests are generally written here at Dwolla.
  */
object JakeRetrievalServiceImplSpec_1_BasicMock
  extends SimpleIOSuite
  with MockitoSugar
  with TestFactory {

  trait Context {
    val storeMock = mock[AccountStore]
    val service: JakeRetrievalService = new JakeRetrievalServiceImpl1(storeMock)
  }

  // Should fail because of the implementation
  test("(should fail) isJake should return true if account exists and is named jake") {
    val ctx = new Context {}
    import ctx._
    val account = createAccount(name = "Jake".some)
    val id = account.accountId

    when(storeMock.get(id)).thenReturn(account.some.pure[IO])

    service.isJake(id).map(isJake => expect(isJake))
  }

  // Now here's one sad path test
  test("(should succeed) isJake should return false if account exists and is not named jake") {
    val ctx = new Context {}
    import ctx._
    val account = createAccount()
    val id = account.accountId

    when(storeMock.get(id)).thenReturn(account.some.pure[IO])

    service.isJake(id).map(isJake => expect(!isJake))
  }

  // Another sad path test
  test("(should succeed) isJake should return false if account does not exist") {
    val ctx = new Context {}
    import ctx._
    val id = uuid()

    when(storeMock.get(id)).thenReturn(None.pure[IO])

    service.isJake(id).map(isJake => expect(!isJake))
  }

  // if the mock isn't used this should succeed
  test("(should succeed) isJake should return false") {
    val ctx = new Context {}
    import ctx._
    val account = createAccount()
    val id = account.accountId

    service.isJake(id).map(isJake => expect(!isJake)) // still succeeds!
  }

  // if the mock is used this should fail
  test("(should fail) isJake should return true if account exists and is named jake") {
    val ctx = new Context {}
    import ctx._
    val account = createAccount(name = "Jake".some)
    val id = account.accountId

    when(storeMock.get(id)).thenReturn(account.some.pure[IO])

    service.isJake(id).map { isJake =>
      // Mockito does not have any special integrations with weaver test, but this line should throw an exception so we can let the test catch it
      verify(storeMock, atLeastOnce).get(id)
      expect(isJake)
    }
  }

}
