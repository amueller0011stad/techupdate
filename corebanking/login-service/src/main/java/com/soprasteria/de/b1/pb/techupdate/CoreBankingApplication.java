package com.soprasteria.de.b1.pb.techupdate;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.soprasteria.de.b1.pb.techupdate.resources.BankResource;
import com.soprasteria.de.b1.pb.techupdate.resources.LogonResource;

/**
 */
@ApplicationPath("/resources")
public class CoreBankingApplication
extends Application
{

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(BankResource.class);
        classes.add(LogonResource.class);
        return classes;
    }

}