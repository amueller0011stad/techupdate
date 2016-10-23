package com.soprasteria.de.b1.pb.techupdate;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;

public class JwtProvider
{
    private static final Logger log = Logger.getLogger("corebanking-login-service");
    
    private RsaJsonWebKey jwk;
    private JwtConsumer jwtConsumer;
    
    public JwtProvider(RsaJsonWebKey jwk)
    {
        this.jwk=jwk;
        jwtConsumer = new JwtConsumerBuilder()
                .setRequireExpirationTime()
                .setMaxFutureValidityInMinutes(300)
                .setAllowedClockSkewInSeconds(30)
                .setVerificationKey(jwk.getKey())
                .build();
    }

    public String getJwt()
    throws JoseException
    {
        JwtClaims claims = new JwtClaims();
        /* Actual groups in the claim are not used ATM */
        claims.setExpirationTimeMinutesInTheFuture(120);
        claims.setGeneratedJwtId();
        claims.setIssuedAtToNow();
        claims.setNotBeforeMinutesInThePast(2);

        JsonWebSignature jws = new JsonWebSignature();

        jws.setPayload(claims.toJson());
        jws.setKey(jwk.getPrivateKey());
        jws.setKeyIdHeaderValue(jwk.getKeyId());
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

        String jwt = jws.getCompactSerialization();
        return jwt;
    }
    
    public boolean checkJwt(String jwt)
    {
        try
        {
            jwtConsumer.processToClaims(jwt);
            /* Actual groups in the claim are not used ATM */
            return true;
        }
        catch (InvalidJwtException e)
        {
            log.log(Level.WARNING,"JWT claim check failed",e);
            return false;
        }
    }
    
}
