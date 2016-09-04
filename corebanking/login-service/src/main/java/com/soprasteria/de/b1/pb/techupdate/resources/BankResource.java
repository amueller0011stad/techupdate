package com.soprasteria.de.b1.pb.techupdate.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.soprasteria.de.b1.pb.techupdate.BankInfo;

/**
 * This is just a stub to provide wire-testing. I reckon that it can be removed
 * after some more meat is in the project. -- RS 2016-07-24
 */
@Path("/bank")
public class BankResource
{

    @GET
    @Path("info")
    @Produces(MediaType.APPLICATION_JSON)
    public BankInfo info()
    {
    	BankInfo info = new BankInfo();
    	info.setName("Sopra Bank");
    	return info;
    }
    
}
