package com.soprasteria.de.b1.pb.techupdate.resources;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.lang.JoseException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soprasteria.de.b1.pb.techupdate.AuthToken;
import com.soprasteria.de.b1.pb.techupdate.JwkDao;
import com.soprasteria.de.b1.pb.techupdate.JwtProvider;
import com.soprasteria.de.b1.pb.techupdate.Login;

/**
 */
@RestController
public class LogonResource
{
    private static final Logger log = Logger.getLogger("corebanking-login-service");
    
    @PersistenceContext
    private EntityManager em;
    
    private JwkDao jwkDao;
    
    public LogonResource(JwkDao jwkDao)
    {
        log.info("Instantiating logon resource");
        this.jwkDao=jwkDao;
    }

    @RequestMapping(path="/logon/{username}")
    @Transactional
    public AuthToken getToken(
    		@PathVariable("username") String username,
    		@RequestParam("password") String password)
    {
    	TypedQuery<Login> loginQuery = em.createNamedQuery(
    	        "Login.byUsername",Login.class);
    	loginQuery.setParameter("username",username);
    	Login login = loginQuery.getSingleResult();
    	boolean pwOk = login.checkPassword(password);
	
    	try
    	{
        	if(pwOk)
        	{
                RsaJsonWebKey key=jwkDao.getOrCreate("corebanking");
                JwtProvider prov = new JwtProvider(key);
        	    AuthToken token = new AuthToken();
        	    token.setUsername(login.getUsername());
        	    token.setJwt(prov.getJwt());
        	    return token;
        	}
    	}
        catch(JoseException e)
    	{
            log.log(Level.WARNING,"Cannot obtain JWT",e);
    	}
    	return null;
    }
    
}
