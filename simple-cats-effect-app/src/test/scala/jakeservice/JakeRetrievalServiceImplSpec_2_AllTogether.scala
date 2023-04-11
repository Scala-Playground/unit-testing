package jakeservice

import jakeservice.database.AccountStore
import jakeservice.services.JakeRetrievalServiceImpl2
import cats.effect.IO
import cats.syntax.all._
import org.mockito.MockitoSugar
import org.typelevel.log4cats.StructuredLogger
import utils.LoggerUtils
import weaver.SimpleIOSuite

object JakeRetrievalServiceImplSpec_2_AllTogether
  extends SimpleIOSuite
  with MockitoSugar
  with TestFactory {

  trait Context {
    val storeMock = mock[AccountStore]
    val loggerMock = LoggerUtils.makeMock // needs a special mock because log4Cats returns `IO[Unit]` when it logs instead of just `Unit`
    val service = new JakeRetrievalServiceImpl2(storeMock) {
      // Sometimes we get noisy tests when a library decides to output logs on the same thread that the test is running on
      // and sometimes we want to use mocks to verify logging statements run.
      // Either way, overriding the mock is common.
      override lazy val slog: StructuredLogger[IO] = loggerMock
    }
  }

  test("(should fail) isJake should return true if account exists and is named jake") {
    val ctx = new Context {}
    import ctx._
    val account = createAccount(name = "Jake".some)
    val id = account.accountId

    service.isJake(id).map(isJake => expect(isJake)) // mock exception!
  }

  // We need to mock the call properly so that it knows what to return when we call it
  test("(should succeed) isJake should return true if account exists and is named jake") {
    val ctx = new Context {}
    import ctx._
    val account = createAccount(name = "Jake".some)
    val id = account.accountId

    when(storeMock.get(id)).thenReturn(account.some.pure[IO])

    service.isJake(id).map(isJake => expect(isJake))
  }

  test("(should succeed) isJake should return false if account exists and is not named jake") {
    val ctx = new Context {}
    import ctx._
    val account = createAccount()
    val id = account.accountId

    when(storeMock.get(id)).thenReturn(account.some.pure[IO])

    service.isJake(id).map(isJake => expect(!isJake))
  }

  test("(should succeed) isJake should return false if account does not exist") {
    val ctx = new Context {}
    import ctx._
    val id = uuid()

    when(storeMock.get(id)).thenReturn(None.pure[IO])

    service.isJake(id).map(isJake => expect(!isJake))
  }

  test("(should succeed) isJake should return true if account exists and is named jake") {
    val ctx = new Context {}
    import ctx._
    val account = createAccount(name = "Jake".some)
    val id = account.accountId

    when(storeMock.get(id)).thenReturn(account.some.pure[IO])

    service.isJake(id).map { isJake =>
      verify(storeMock, atLeastOnce).get(id)
      expect(isJake)
    }
  }

}
