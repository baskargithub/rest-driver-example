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

### It didn't work

By default the acceptance tests will fire-up a [Jetty](http://www.eclipse.org/jetty/) instance on port 8080 to run the sample webapp and will use port 8081 to run the client driver instance which will pretend to be any dependent services.
