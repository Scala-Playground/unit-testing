## Example 3: Simple IO App

The tests here are meant to resemble the ones that you might see in a basic service implementation:

```
..
 |
 |
 v database
 |   |
 |   * AccountStore              <- account storage interface definition (i.e. no real code)
 |
 v model
 |   |
 |   * Account                   <- basic object being manipulated
 |   
 v services
 |   |
 |   * JakeRetreivalService      <- interface definition for service build on top of account storage service
 |   |
 |   * JakeRetrievalServiceImpl1 <- initial implementation of interface (i.e. has real code)
 |   |
 |   * JakeRetrievalServiceImpl2 <- a second (more complete) implementation of the interface
 
```

If we follow the common patterns in the majority of our repos here at Dwolla, we would find that we normally only create
unit tests for the implementations of the services.
If we only have one implementation then it is certainly simpler to do that, but that would mean that the tests are being
written after the code is written.
This is not uncommon, but it can make our interface definitions sloppy or even downright unusable.
Test Driven Development™ is a commonly adopted strategy to avoid this pitfall, and these tests are meant to serve as an
example of how that may play out.

* First, we should look at the `JakeRetrievalService` interface definition.
* Then, we write the *absolute simplest* implementation and write our tests around that. (`JakeRetrievalServiceImpl1`)
* Finally, we make the implementation more complicated until all the tests pass. (`JakeRetrievalServiceImpl2`)

