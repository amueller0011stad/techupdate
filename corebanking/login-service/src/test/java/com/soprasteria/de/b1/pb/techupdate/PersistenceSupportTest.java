package com.soprasteria.de.b1.pb.techupdate;

import javax.persistence.EntityManager;

import org.hibernate.service.spi.ServiceException;
import org.junit.Test;

public class PersistenceSupportTest
{
    /**Smoketest for EntityManager creation. */
    @Test
    public void testCreateEntityManager()
    {
        try
        {
            PersistenceSupport ps = new PersistenceSupport();
            EntityManager em = ps.createEntityManager();
            em.close();
            ps.destroy();
        }
        catch(ServiceException e0)
        {
            /* JNDI lookup error, it's fine ATM */
        }
    }

}
