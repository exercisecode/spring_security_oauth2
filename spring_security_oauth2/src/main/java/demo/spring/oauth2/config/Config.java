package demo.spring.oauth2.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.stereotype.Service;



//@Service
public class Config {
	
	
	//@Autowired
	private DataSource dataSource;
	
	
	
	//@Bean
	public TokenStore jdbcTokenStore(){
		TokenStore tokenStore = new JdbcTokenStore(dataSource);
		return tokenStore;
	}
	
	//@Bean
	public DefaultTokenServices defaultTokenService(){
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(jdbcTokenStore());
		defaultTokenServices.setSupportRefreshToken(true);
		
		return defaultTokenServices;
	}
	
	//@Bean
	public ClientDetailsService clientDetailsService(){
		ClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
		return clientDetailsService;
	}
	
	//@Bean
	public OAuth2AuthenticationEntryPoint oAuth2AuthenticationEntryPoint(){
		OAuth2AuthenticationEntryPoint oAuth2AuthenticationEntryPoint = new OAuth2AuthenticationEntryPoint();
		return oAuth2AuthenticationEntryPoint;
	}
	 

}
