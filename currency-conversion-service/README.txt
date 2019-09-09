Feign - Its an alternative to Rest template. It is a restful web-services client which will make easy to call other microservices
Ribbon - Ribbon is the client side load balancer which is used to distribute the load between different instances of currency-exchnage-service
Eureka - To link any micro service too the load balancer like ribbon the instances needs to be maintained in the application.properties Whenever we need to increase or decrease the instances the application.properties of the micro service needs to be changed which is a drawback so Eureka naming server is used which is provided by netflix

To implement Eureka a server needs to be implemented netflix-eureka-naming-server
To make your sevices enable as Eureka client below are the steps to be done
1) application.properties - add the url of Eureka server
2) In the java main call add @EnableDiscoveryClient
3) In pom.xml add spring-cloud-starter-eureka as dependency

Zuul - Zull is the API gate way for all the microservices. Its recommented that any common features for the Web-services like Authentication, Autarizarion, Logging can be implemented in the Zuul server so all the calls to the services are intercepted by the Zuul server.

To implement Zuul api gate way netflix-zuul-api-gateway-server needs to be created

Tag the zuul server to Eureka server

sleuth - Since there will be multiple micro services intracting with each other and each service having its own log. Its difficult to debug if any issue occurs. So spring-cloud-starter-sleuth is used, which will create unique id for each incoming request to microservices. This id can be seen in the logs for the servers for which sleuth is implemented. 

Below are the steps to implement sleuth
1) In pom.xml add spring-cloud-starter-sleuth as dependency
2) For the services for which you would like to implement this add 
@Bean
public AlwaysSampler defaultSampler(){return new AlwaysSampler();}

Since its difficult to open logs of each microservices and trace based on the unique id. Zipkin distributed Tracing server is used to cetralize all the logs

Zipkin with Rabbit MQ -
Cloud Bus -