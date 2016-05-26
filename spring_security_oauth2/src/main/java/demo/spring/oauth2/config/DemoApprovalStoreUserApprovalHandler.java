package demo.spring.oauth2.config;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.approval.ApprovalStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

public class DemoApprovalStoreUserApprovalHandler extends ApprovalStoreUserApprovalHandler {
	
	private boolean useApprovalStore = true;
	
	private JdbcClientDetailsService clientDetailsService;
	
	public void setClientDetailsService(JdbcClientDetailsService clientDetailsService){
		this.clientDetailsService = clientDetailsService;
		super.setClientDetailsService(clientDetailsService);
	}
	
	public void setUseApprovalStore(boolean useApprovalStore){
		this.useApprovalStore = useApprovalStore;
	}
	
	@Override
	public AuthorizationRequest checkForPreApproval(AuthorizationRequest authorizationRequest, Authentication userAuthentication){
		boolean approved = false;
		if(useApprovalStore){
			authorizationRequest = super.checkForPreApproval(authorizationRequest, userAuthentication);
			approved = authorizationRequest.isApproved();
		}else{
			if(clientDetailsService != null){
				Collection<String> requestedScopes = authorizationRequest.getScope();
				try{
					ClientDetails client = clientDetailsService.loadClientByClientId(authorizationRequest.getClientId());
					for(String scope : requestedScopes){
						if(client.isAutoApprove(scope)){
							approved = true;
							break;
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
		}
		
		authorizationRequest.setApproved(approved);
		return authorizationRequest;
	}

}
