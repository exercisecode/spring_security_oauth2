package demo.spring.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


@SpringBootApplication
@EnableAuthorizationServer
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringOAuthMain {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext configContext = SpringApplication.run(SpringOAuthMain.class, args);
		
		
		System.out.println("\n\n\n");
		for(String beanName : configContext.getBeanDefinitionNames()){
			System.out.println("\t " + beanName);
		}
		
		System.out.println("\n\n beanCount= " + configContext.getBeanDefinitionCount());
		
		
	}

}
