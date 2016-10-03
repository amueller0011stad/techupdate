package com.soprasteria.de.b1.pb.techupdate;

import java.util.Properties;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.DisposableBean;

public class PersistenceSupport
implements DisposableBean
{
    private static final Logger log = Logger.getLogger("corebanking-login-service");
    
    private EntityManagerFactory emf;
    
    PersistenceSupport()
    {
        log.info("Creating EntityManagerFactory");
        createEntityManagerFactory();
    }
    
    private void createEntityManagerFactory()
    {
        //final Properties config=new Properties();
        
        emf=Persistence.createEntityManagerFactory("corebankingLoginService");
    }
    
    public EntityManager createEntityManager()
    {
        return emf.createEntityManager();
    }
    
    public void destroy()
    {
        log.info("Closing EntityManagerFactory");
        emf.close();
    }
}
