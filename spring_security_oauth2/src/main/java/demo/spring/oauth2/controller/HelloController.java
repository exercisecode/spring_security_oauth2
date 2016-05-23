package demo.spring.oauth2.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public Map<String, String> hello(@RequestParam(name = "username", required = false) String username){
		Map<String, String> helloMessage = new LinkedHashMap<String, String>();
		
		helloMessage.put("message","success");
		helloMessage.put("username",username);
		helloMessage.put("returnMessage", " Hello , " + username);
		
		return helloMessage;
	}

}
