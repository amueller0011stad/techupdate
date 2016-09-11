package com.soprasteria.de.b1.pb.techupdate.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.soprasteria.de.b1.pb.techupdate.BankInfo;

/**
 * This is just a stub to provide wire-testing. I reckon that it can be removed
 * after some more meat is in the project. -- RS 2016-07-24
 */
@RestController
public class BankResource
{

    @RequestMapping(path="/info",method=RequestMethod.GET)
    public BankInfo info()
    {
    	BankInfo info = new BankInfo();
    	info.setName("Sopra Bank");
    	return info;
    }
    
}
