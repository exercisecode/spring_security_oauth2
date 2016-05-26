package demo.spring.oauth2.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.stereotype.Service;

@Service
public class Config {
	
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public JdbcTokenStore jdbcTokenStore(){
		return new JdbcTokenStore(dataSource);
	}

}
