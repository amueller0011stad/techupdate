package com.soprasteria.de.b1.pb.techupdate.resources;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.soprasteria.de.b1.pb.techupdate.AuthToken;

/**
 */
@Path("/logon")
public class LogonResource
{

    @POST
    @Path("{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public AuthToken getToken(
    		@PathParam("username") String username,
    		@QueryParam("password") String password)
    {
    	AuthToken token = new AuthToken();
    	/* Check password here XXX
    	 * Replace this with RFC 7519 tokens!!! XXX */
    	
    	token.setUsername(username.toUpperCase()/*just to check whether we're called */);
    	return token;
    }
    
}
