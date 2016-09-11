package com.soprasteria.de.b1.pb.techupdate.resources;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soprasteria.de.b1.pb.techupdate.AuthToken;

/**
 */
@RestController
public class LogonResource
{

    @RequestMapping(path="/logon/{username}",method=RequestMethod.POST)
    public AuthToken getToken(
    		@PathVariable("username") String username,
    		@RequestParam("password") String password)
    {
    	AuthToken token = new AuthToken();
    	/* Check password here XXX
    	 * Replace this with RFC 7519 tokens!!! XXX */
    	
    	token.setUsername(username.toUpperCase()/*just to check whether we're called */);
    	return token;
    }
    
}
