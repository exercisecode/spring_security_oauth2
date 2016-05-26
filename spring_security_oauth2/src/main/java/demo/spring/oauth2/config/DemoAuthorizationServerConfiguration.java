package demo.spring.oauth2.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenEndpointFilter;
import org.springframework.security.oauth2.provider.client.ClientDetailsUserDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

//@Configuration
//@EnableAuthorizationServer
public class DemoAuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	

	
	
	@Autowired
	private TokenStore tokenStore;
	
	@Autowired
	@Lazy
	private DefaultTokenServices defaultTokenServices;
	
	@Autowired
	private JdbcClientDetailsService jdbcClientDetailsService;
	
	@Autowired
	private ApprovalStore approvalStore;
	
	@Autowired
	private UserApprovalHandler userApprovalHandler;
	
	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;
	
	@Bean
	public TokenStore tokenStore(){
		TokenStore tokenStore = new JdbcTokenStore(dataSource);
		return tokenStore;
	}
	
	

	
	@Bean
	public ApprovalStore approcalStore() throws Exception{
		TokenApprovalStore approvalStore = new TokenApprovalStore();
		approvalStore.setTokenStore(tokenStore);
		return approvalStore;
	}
	
	@Bean
	public DefaultTokenServices defaultTokenServices(){
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore);
		defaultTokenServices.setSupportRefreshToken(true);
		//defaultTokenServices.setClientDetailsService(jdbcClientDetailsService);
		//defaultTokenServices.setAuthenticationManager(authenticationManager);
		return defaultTokenServices;
	}
	
	@Bean
	public JdbcClientDetailsService jdbcClientDetailsService(){
		JdbcClientDetailsService jdbcClientDetailsService = new JdbcClientDetailsService(dataSource);
		return jdbcClientDetailsService;
	}
	
	@Bean
	public ClientDetailsUserDetailsService clientDetailsUserDetailsService(){
		ClientDetailsUserDetailsService clientDetailsUserDetailsService = new ClientDetailsUserDetailsService(jdbcClientDetailsService);
		return clientDetailsUserDetailsService;
	}
	
	@Bean
	public OAuth2AuthenticationEntryPoint oAuth2AuthenticationEntryPoint(){
		OAuth2AuthenticationEntryPoint oAuth2AuthenticationEntryPoint = new OAuth2AuthenticationEntryPoint();
		return oAuth2AuthenticationEntryPoint;
	}
	
	@Bean
	public OAuth2AuthenticationManager oAuth2AuthenticationManager(){
		OAuth2AuthenticationManager oAuth2AuthenticationManager = new OAuth2AuthenticationManager();
		oAuth2AuthenticationManager.setClientDetailsService(jdbcClientDetailsService);
		//DefaultTokenServices tokenService = new DefaultTokenServices();
		//tokenService.setSupportRefreshToken(true);
		oAuth2AuthenticationManager.setTokenServices(defaultTokenServices);
		
		return oAuth2AuthenticationManager;
	}
	
	@Bean
	public OAuth2AccessDeniedHandler oAuth2AccessDeniedHandler(){
		OAuth2AccessDeniedHandler oAuth2AccessDeniedHandler = new OAuth2AccessDeniedHandler();
		return oAuth2AccessDeniedHandler;
	}
	
	@Bean
	public UserApprovalHandler userApprovalHandler() throws Exception{
		DemoApprovalStoreUserApprovalHandler userApprovalHandler = new DemoApprovalStoreUserApprovalHandler();
		userApprovalHandler.setApprovalStore(approvalStore);
		userApprovalHandler.setRequestFactory(new DefaultOAuth2RequestFactory(jdbcClientDetailsService));
		userApprovalHandler.setClientDetailsService(jdbcClientDetailsService);
		return userApprovalHandler;
	}
	
	@Bean
	public ClientCredentialsTokenEndpointFilter clientCredentialTokenEndpointFilter(){
		ClientCredentialsTokenEndpointFilter clientCredentialsTokenEndpointFilter = new ClientCredentialsTokenEndpointFilter();
		clientCredentialsTokenEndpointFilter.setAuthenticationManager(authenticationManager);
		return clientCredentialsTokenEndpointFilter;
	}



	

	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception{
		endpoints.tokenStore(tokenStore).userApprovalHandler(userApprovalHandler)
		.authenticationManager(authenticationManager).setClientDetailsService(jdbcClientDetailsService);
	}
	

}
