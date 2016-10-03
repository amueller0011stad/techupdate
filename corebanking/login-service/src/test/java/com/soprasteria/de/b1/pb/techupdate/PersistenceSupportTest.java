package com.soprasteria.de.b1.pb.techupdate;

import javax.persistence.EntityManager;

import org.junit.Test;

public class PersistenceSupportTest
{
    /**Smoketest for EntityManager creation. */
    @Test
    public void testCreateEntityManager()
    {
        PersistenceSupport ps = new PersistenceSupport();
        EntityManager em = ps.createEntityManager();
        em.close();
        ps.destroy();
    }

}
