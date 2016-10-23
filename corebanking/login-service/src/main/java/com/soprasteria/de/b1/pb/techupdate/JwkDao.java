package com.soprasteria.de.b1.pb.techupdate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.JsonWebKey.OutputControlLevel;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.lang.JoseException;

public class JwkDao
{
    private static final Logger log = Logger.getLogger("corebanking-login-service");
    
    @PersistenceContext
    private EntityManager em;

    public RsaJsonWebKey findById(String keyId)
    throws JoseException
    {
        TypedQuery<JwkParam> loginQuery = em.createNamedQuery(
                "JwkParam.byKeyId",JwkParam.class);
        loginQuery.setParameter("keyId",keyId);
        List<JwkParam> params = loginQuery.getResultList();
        log.log(Level.FINE,"Obtained {0} result(s) for keyId {1}",new Object[]{
                params.size(),keyId});
        if(params.size()==0) return null;
        Map<String,Object> paramMap = new HashMap<String,Object>();
        for(JwkParam param : params) paramMap.put(param.getKey(),param.getValue());
        return (RsaJsonWebKey)JsonWebKey.Factory.newJwk(paramMap);
    }
    
    public RsaJsonWebKey getOrCreate(String keyId)
    throws JoseException
    {
        RsaJsonWebKey jwk = findById(keyId);
        if(jwk==null)
        {
            log.log(Level.FINE,"Key with keyId {0} not found, generating",keyId);
            jwk = RsaJwkGenerator.generateJwk(2048);
            save(keyId,jwk);
        }
        return jwk;
    }
    
    public void save(String keyId,RsaJsonWebKey jwk)
    {
        TypedQuery<JwkParam> loginQuery = em.createNamedQuery(
                "JwkParam.byKeyId",JwkParam.class);
        loginQuery.setParameter("keyId",keyId);
        List<JwkParam> params = loginQuery.getResultList();
        if(params.size()>0)
        {
            for(JwkParam param : params) em.remove(param);
        }
        Map<String,Object> newJwk=jwk.toParams(OutputControlLevel.INCLUDE_PRIVATE);
        for(String key : newJwk.keySet())
        {
            JwkParam param = new JwkParam();
            param.setKeyId(keyId);
            param.setKey(key);
            param.setValue(String.valueOf(newJwk.get(key)));
            em.persist(param);
        }
        log.log(Level.FINE,"Saved new key with keyId {0}",keyId);
    }
}
