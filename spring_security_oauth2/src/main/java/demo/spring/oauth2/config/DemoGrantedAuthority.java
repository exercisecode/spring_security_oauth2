package demo.spring.oauth2.config;

import org.springframework.security.core.GrantedAuthority;

public class DemoGrantedAuthority implements GrantedAuthority {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String authority;
	
	public DemoGrantedAuthority(String authority){
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.authority;
	}

}
