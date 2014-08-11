parallel-streams-example
========================

Example code for https://speakerdeck.com/fstab/java-8-mehrere-prozessorkerne-effizient-nutzen-mit-lambdas-und-streams

A deployable WAR file can be built with

    mvn clean package

The WAR is created in `webapp/target/parallel-streams-example.war`. It was tested with the [Wildfly](http://wildfly.org) application server.

Benchmarks can be run with

    mvn clean package
    java -jar benchmark/target/benchmarks.jar .*MyBenchmark.* -f 1
