package com.soprasteria.de.b1.pb.techupdate.resources;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soprasteria.de.b1.pb.techupdate.AuthToken;
import com.soprasteria.de.b1.pb.techupdate.Login;
import com.soprasteria.de.b1.pb.techupdate.LoginUtil;
import com.soprasteria.de.b1.pb.techupdate.PersistenceSupport;

/**
 */
@RestController
public class LogonResource
{
    private static final Logger log = Logger.getLogger("corebanking-login-service");
    
    private PersistenceSupport persistence;
    
    public LogonResource(PersistenceSupport persistence)
    {
        log.info("Instantiating logon resource");
        this.persistence = persistence;
    }

    @RequestMapping(path="/logon/{username}")
    public AuthToken getToken(
    		@PathVariable("username") String username,
    		@RequestParam("password") String password)
    {
    	EntityManager em = null;
    	try
    	{
    	    em = persistence.createEntityManager();
        	TypedQuery<Login> loginQuery = em.createNamedQuery(
        	        "Login.byUsername",Login.class);
        	loginQuery.setParameter("username",username);
        	Login login = loginQuery.getSingleResult();
        	boolean pwOk = login.checkPassword(password);
    	
        	/* Replace this with RFC 7519 tokens!!! XXX */
        	
        	if(pwOk)
        	{
        	    AuthToken token = new AuthToken();
        	    token.setUsername(login.getUsername());
        	    return token;
        	}
        	return null;
    	}
    	finally
    	{
    	    if(em!=null) em.close();
    	}
    }
    
}
