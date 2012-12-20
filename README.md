Apache Syncope overlay to be run in Windows Azure, featuring [OpenJPA-Azure](https://github.com/Tirasa/OpenJPA-Azure).<br/>
Practical implementation of official advices at Syncope [wiki](https://cwiki.apache.org/confluence/display/SYNCOPE/Run+Syncope+in+real+environments).

## How to test ##

This projects assumes that you have
 1. [Apache Maven 3.0](http://maven.apache.org) installed
 1. valid Windows Azure subscription
 1. a federated database called syncope with a federation UsersFed with five members.

#### clone ####

<pre>
$ git clone git://github.com/Tirasa/syncopeOnAzure.git
</pre>

#### build ####

<pre>
$ cd syncopeOnAzure
$ mvn clean package
</pre>

#### deploy ####
There are many available options: the easiest is probably to [deploy Apache Tomcat](http://techyfreak.blogspot.it/2011/03/installing-tomcat-in-windows-azure.html)
bundled with the following two web applications:
 1. <code>core/target/syncope.war</code>
 1. <code>console/target/syncope-console.war</code>

Please, consider that each slice refers a specific datasource:
 1. slice ROOT uses jdbc/root datasource;
 1. slice UsersFed.0 uses jdbc/userfed0;
 1. slice UsersFed.1 uses jdbc/userfed1.
 1. slice UsersFed.2 uses jdbc/userfed2.
 1. slice UsersFed.3 uses jdbc/userfed3.
 1. slice UsersFed.4 uses jdbc/userfed4.
You have to take care to make available these datasources to the web application syncope.
In case you are using Apache Tomcat as container you can copy the provided context.xml file into <TOMCAT_HOME>/conf.

## Notes ##
 1. On 1.0.2-incubating
 1. Not listenig on localhost:8080? Just put the correct port in <code>console/src/main/resources/configuration.properties</code>, re-build and re-deploy

## Need more info? ##
Just drop an [e-mail](mailto:openjpasqlazure@tirasa.net).
