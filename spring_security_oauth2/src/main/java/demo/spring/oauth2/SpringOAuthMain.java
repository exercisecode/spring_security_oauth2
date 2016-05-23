package demo.spring.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringOAuthMain {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext configContext = SpringApplication.run(SpringOAuthMain.class, args);
		
		System.out.println("\n\n beanCount= " + configContext.getBeanDefinitionCount());
		
		
	}

}
