# spring_security_oauth2
spring security oauth2


Spring Boot  
[http://projects.spring.io/spring-boot/](http://projects.spring.io/spring-boot/)  


http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-security-oauth2-token-type  

http://projects.spring.io/spring-security-oauth/  

http://projects.spring.io/spring-security-oauth/docs/oauth2.html  

http://docs.spring.io/spring-security/site/docs/4.0.4.RELEASE/reference/htmlsingle/#preface

https://github.com/spring-projects/spring-security-oauth/blob/master/spring-security-oauth2/src/test/resources/schema.sql

http://spring.io/guides/tutorials/spring-boot-oauth2/


demo  
https://github.com/spring-projects/spring-security-oauth/tree/master/samples/oauth2  

https://github.com/spring-projects/spring-boot/tree/v1.3.5.RELEASE/spring-boot-samples  

https://github.com/spring-projects/spring-boot/tree/v1.3.5.RELEASE/spring-boot-samples/spring-boot-sample-secure-oauth2  

https://github.com/spring-projects/spring-boot/tree/v1.3.5.RELEASE/spring-boot-samples/spring-boot-sample-secure-oauth2-resource  


https://github.com/spring-projects/spring-security-oauth/blob/master/docs/oauth2.md  





AuthorizationEndpoint  /oauth/authorize  

OAuth2Utils  
response_type token, code  





@EnableAutoConfiguration  
@SpringBootApplication  

SpringApplication  
ConfigurableApplicationContext  


http://projects.spring.io/spring-security-oauth/docs/Home.html  


What is spring security  
http://docs.spring.io/spring-security/site/docs/4.1.0.RELEASE/reference/htmlsingle/#what-is-acegi-security  


http://127.0.0.1:20001/oauth/authorize  


Mapped {[/oauth/authorize]}
Mapped {[/oauth/authorize],methods=[POST],params=[user_oauth_approval]}
Mapped {[/oauth/token],methods=[GET]}
Mapped {[/oauth/token],methods=[POST]}
Mapped {[/oauth/check_token]}
Mapped {[/oauth/confirm_access]}
Mapped {[/oauth/error]}




$ curl myclient:democlient@127.0.0.1:20001/oauth/token -d grant_type=password -d username=user -d password=07bb2958-8991-488b-92a9-f3a371049a1f
{"error":"invalid_scope","error_description":"Empty scope (either the client or the user is not allowed the requested scopes)"}


$ curl myclient:democlient@127.0.0.1:20001/oauth/token -d grant_type=password -d username=username -d password=20161001
{"error":"invalid_scope","error_description":"Empty scope (either the client or the user is not allowed the requested scopes)"}


$ curl 127.0.0.1:20001/oauth/token -d "grant_type=password&scope=rw&username=username&password=20161001" -u myclient:democlient
{"access_token":"7c1e7888-7300-4caa-97f2-54bbf55034cc","token_type":"bearer","refresh_token":"c3bc1113-2dc8-4f8c-a6b9-91d57dafb565","expires_in":37936,"scope":"rw"}


$ curl -H "Authorization: bearer 7c1e7888-7300-4caa-97f2-54bbf55034cc" 127.0.0.1:20001/hello?username=World
{"message":"success","username":"World","returnMessage":" Hello , World"}






