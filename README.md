Apache Syncope overlay to be run in Windows Azure, featuring [OpenJPA-Azure](https://github.com/Tirasa/OpenJPA-Azure).<br/>
Practical implementation of official advices at Syncope [wiki](https://cwiki.apache.org/confluence/display/SYNCOPE/Run+Syncope+in+real+environments).

## How to test ##

This projects assumes that you have
 1. [Apache Maven 3.0](http://maven.apache.org) installed
 1. valid Windows Azure subscription

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

## Notes ##
 1. On 1.0.2-incubating
 1. Not listenig on localhost:8080? Just put the correct port in <code>console/src/main/resources/configuration.properties</code>, re-build and re-deploy

## Need more info? ##
Just drop an [e-mail](mailto:openjpasqlazure@tirasa.net).
