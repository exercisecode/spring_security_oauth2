package demo.spring.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class DemoWebSecurityConfigurerAdapater extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DemoUserDetailsService userDetailsService;

	@Autowired
	private DemoPasswordEncoder demoPasswordEncoder;
	
	@Autowired
	public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception{
		//auth.jdbcAuthentication();
		auth.userDetailsService(userDetailsService).passwordEncoder(demoPasswordEncoder);
		//auth.jdbcAuthentication().dataSource(dataSource);
		//auth.inMemoryAuthentication();
		
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception{
		AuthenticationManager authenticationManager = super.authenticationManagerBean();
		//Authentication authentication = null;
		//authenticationManager.
		
		return authenticationManager;
	}
}
