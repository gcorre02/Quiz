Quiz System
====
Some javadocs are missing in a few classes and some tests are not totally implemented. otherwise, all expected behaviour works.
====

Running (Parenthesis are just explaining what things are):


Run server version  

server

java -Djava.security.policy=security.policy LaunchServerMain(.class) systemFolder(arg)

client

java -Djava.security.policy=security.policy ServerMain(.class) systemFolder(arg)



Run Local Version

java LocalMain(.class) systemFolder(arg)



====

Libraries used:

Gson 2.2.4    (Persistence)

Junit 4.11    (Testing)

Lombok 1.12.6 (@ Code completion)

Mockito 1.9.5 (Mocking)



====

TESTING

Testing is run centrally, through class TestSuite inside package test.

Needs running with -Djava.security.policy=security.policy

For individual testing, SetupSystemFiles must be called by the test suite handling the smaller scope of tests. 
