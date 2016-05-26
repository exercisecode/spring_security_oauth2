package demo.spring.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configuration.ClientDetailsServiceConfiguration;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

//@Configuration
public class DemoClientDetailsServiceConfiguration extends ClientDetailsServiceConfiguration {
	
	@Autowired
	private JdbcClientDetailsService jdbcClientDetailsService;
	
	@Override
	public ClientDetailsService clientDetailsService() throws Exception{
		return jdbcClientDetailsService;
	}

}
