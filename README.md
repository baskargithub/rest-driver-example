# REST-driver example project

_Be warned: This is very much a work in progress_

This is an example [Maven](http://maven.apache.org/) project which is intended to demonstrate how we make use of both the server and client driver parts of the [REST-driver](https://github.com/rest-driver/rest-driver) here at Nokia Entertainment.

The parent project has two child modules:

* webapp - A simple sample web application which is the target of our tests.
* acceptance-tests - Some acceptance tests which are used to test the webapp.

## Getting started

Once you've grabbed the source from github and you're ready to muck about with Maven let's try running the tests:

```
mvn verify
```

If you saw a lot of junk pumped out on your terminal followed by `BUILD SUCCESS` then you can skip the next bit.

### HALP! It didn't work...

Without modification the tests should all pass (unless we've really screwed up a commit) so any problems are most likely to be down to issues with obtaining one or more of the ports that the sample tests use.

By default the acceptance tests will fire-up a [Jetty](http://www.eclipse.org/jetty/) instance which runs the sample webapp on port 8080 and the client driver will listen on port 8081 and will pretend to be the sample webapp's dependent service.

If you need to alter the port that the sample webapp is listening on then you can add `-DcontainerPort=<port>` to the Maven command you launch the tests with. You can also alter the port that the client driver listens on by adding `-DclientDriverPort=<port>` to your Maven command.

Hopefully if you take a look at the error messages it should be clear which (or both) of the ports you need to change.

### So it succeeded, what happened?

What you just witnessed was Maven compiling and packaging the webapp. The acceptance test project is then compiled and the [Cargo](http://cargo.codehaus.org/Maven2+plugin) plugin fires-up that Jetty instance we mentioned earlier. While the webapp is running the acceptance tests are run against it. Finally, Cargo shuts the webapp down and we're all done.

This is how we test our services here at Nokia Entertainment. We think it works well and allows a pretty quick testing turnaround.

### What's included in the sample acceptance tests?

There are some annotated [server driver tests](https://github.com/rest-driver/rest-driver-example/blob/master/acceptance-tests/src/test/java/com/github/restdriver/example/ServerDriverExampleTest.java). These tests demonstrate features of the server driver when calling our [sample webapp](https://github.com/rest-driver/rest-driver-example/blob/master/webapp/src/main/java/com/github/restdriver/example/Sample.java).

There are some annotated [client driver tests](https://github.com/rest-driver/rest-driver-example/blob/master/acceptance-tests/src/test/java/com/github/restdriver/example/ClientDriverExampleTest.java). These tests demonstrate features of the client driver when calling the part of the sample webapp which contacts another service.