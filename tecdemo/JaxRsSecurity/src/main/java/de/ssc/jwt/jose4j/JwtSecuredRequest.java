package de.ssc.jwt.jose4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.JsonWebKeySet;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;

@Path("/security")
public class JwtSecuredRequest {
	static Logger logger = Logger.getLogger(JwtSecuredRequest.class);
	static List<JsonWebKey> jwkList = null;

	static {
		logger.info("Inside static initializer...");
		jwkList = new LinkedList<>();
		// Creating three keys, will use one now, maybe rework this to be more flexible -- if time permits
		for (int kid = 1; kid <= 3; kid++) {
			JsonWebKey jwk = null;
			try {
				jwk = RsaJwkGenerator.generateJwk(2048);
				logger.info("PUBLIC KEY (" + kid + "): " + jwk.toJson(JsonWebKey.OutputControlLevel.PUBLIC_ONLY));
			} catch (JoseException e) {
				e.printStackTrace();
			}
			jwk.setKeyId(String.valueOf(kid));
			jwkList.add(jwk);
		}

	}

	@Path("/status")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion() {
		return "JwtSecuredRequest Status is OK...";
	}

	@Path("/authenticate")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response authenticateCredentials(
			@HeaderParam("username") String username, 
			@HeaderParam("password") String password)
			throws JsonGenerationException, JsonMappingException, IOException {

		logger.info("Authenticating User Credentials...");

		if (username == null) {
			StatusMessage statusMessage = new StatusMessage();
			statusMessage.setStatus(Status.PRECONDITION_FAILED.getStatusCode());
			statusMessage.setMessage("Username value is missing!!!");
			return Response.status(Status.PRECONDITION_FAILED.getStatusCode()).entity(statusMessage).build();
		}

		if (password == null) {
			StatusMessage statusMessage = new StatusMessage();
			statusMessage.setStatus(Status.PRECONDITION_FAILED.getStatusCode());
			statusMessage.setMessage("Password value is missing!!!");
			return Response.status(Status.PRECONDITION_FAILED.getStatusCode()).entity(statusMessage).build();
		}

		User user = validUser(username, password);
		if (user == null) {
			StatusMessage statusMessage = new StatusMessage();
			statusMessage.setStatus(Status.FORBIDDEN.getStatusCode());
			statusMessage.setMessage("Access Denied for this functionality !!!");
			return Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMessage).build();
		}

		RsaJsonWebKey senderJwk = (RsaJsonWebKey) jwkList.get(0);

		senderJwk.setKeyId("1");
		logger.info("JWK (1) ===> " + senderJwk.toJson());

		// Create the Claims, which will be the content of the JWT
		JwtClaims claims = new JwtClaims();
		claims.setIssuer("ssc.de"); // who creates the token and signs it
		claims.setExpirationTimeMinutesInTheFuture(10); // token will expire (10 minutes from now)
		claims.setGeneratedJwtId(); // a unique identifier for the token
		claims.setIssuedAtToNow(); // when the token was issued/created (now)
		claims.setNotBeforeMinutesInThePast(2); // time before which the token is not yet valid (2 minutes ago)
		claims.setSubject(user.getUsername()); // the subject/principal is whom the token is about
		claims.setStringListClaim("roles", user.getRolesList()); // multi-valued claims for roles
		JsonWebSignature jws = new JsonWebSignature();

		jws.setPayload(claims.toJson());

		jws.setKeyIdHeaderValue(senderJwk.getKeyId());
		jws.setKey(senderJwk.getPrivateKey());

		jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

		String jwt = null;
		try {
			jwt = jws.getCompactSerialization();
		} catch (JoseException e) {
			e.printStackTrace();
		}

		return Response.status(200).entity(jwt).build();
	}

	// --- Protected resource using service-id and api-key ---
	@Path("/finditembyid")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findItemById(
			@HeaderParam("token") String token, 
			@QueryParam("itemid") String item_id)
			throws JsonGenerationException, JsonMappingException, IOException {

		logger.info("Inside findOrderById...");

		if (token == null) {
			StatusMessage statusMessage = new StatusMessage();
			statusMessage.setStatus(Status.FORBIDDEN.getStatusCode());
			statusMessage.setMessage("Access Denied for this functionality !!!");
			return Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMessage).build();
		}

		JsonWebKeySet jwks = new JsonWebKeySet(jwkList);
		JsonWebKey jwk = jwks.findJsonWebKey("1", null, null, null);
		logger.info("JWK (1) ===> " + jwk.toJson());

		// Validate Token's authenticity and check claims
		JwtConsumer jwtConsumer = new JwtConsumerBuilder()
				.setRequireExpirationTime()
				.setAllowedClockSkewInSeconds(30)
				.setRequireSubject()
				.setExpectedIssuer("ssc.de")
				.setVerificationKey(jwk.getKey())
				.build();

		try {
			// Validate the JWT and process it to the Claims
			JwtClaims jwtClaims = jwtConsumer.processToClaims(token);
			logger.info("JWT validation succeeded! " + jwtClaims);
		} catch (InvalidJwtException e) {
			logger.error("JWT is Invalid: " + e);
			StatusMessage statusMessage = new StatusMessage();
			statusMessage.setStatus(Status.FORBIDDEN.getStatusCode());
			statusMessage.setMessage("Access Denied for this functionality !!!");
			return Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMessage).build();
		}

		// Demo-Item erzeugen
		Item item = new Item();
		item.setItemId(item_id);
		item.setItemName("Hallo");
		item.setItemPrice(1.45);

		return Response.status(200).entity(item).build();
	}

	// --- Protected resource using JWT Token ---
	@Path("/showallitems")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response showAllItems(@HeaderParam("token") String token) throws JsonGenerationException, JsonMappingException, IOException {

		logger.info("Inside showAllItems...");

		if (token == null) {
			StatusMessage statusMessage = new StatusMessage();
			statusMessage.setStatus(Status.FORBIDDEN.getStatusCode());
			statusMessage.setMessage("Access Denied for this functionality !!!");
			return Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMessage).build();
		}

		JsonWebKeySet jwks = new JsonWebKeySet(jwkList);
		JsonWebKey jwk = jwks.findJsonWebKey("1", null, null, null);
		logger.info("JWK (1) ===> " + jwk.toJson());

		// Validate Token's authenticity and check claims
		JwtConsumer jwtConsumer = new JwtConsumerBuilder()
				.setRequireExpirationTime()
				.setAllowedClockSkewInSeconds(30) // allow for a 30 second difference to
				.setRequireSubject()
				.setExpectedIssuer("ssc.de")
				.setVerificationKey(jwk.getKey())
				.build(); // create the JwtConsumer instance

		try {
			// Validate the JWT and process it to the Claims
			JwtClaims jwtClaims = jwtConsumer.processToClaims(token);
			logger.info("JWT validation succeeded! " + jwtClaims);
		} catch (InvalidJwtException e) {
			logger.error("JWT is Invalid: " + e);
			StatusMessage statusMessage = new StatusMessage();
			statusMessage.setStatus(Status.FORBIDDEN.getStatusCode());
			statusMessage.setMessage("Access Denied for this functionality !!!");
			return Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMessage).build();
		}

		List<Item> allItems = new ArrayList<Item>();

		Item item = new Item();
		item.setItemId("1");
		item.setItemName("Hallo");
		item.setItemPrice(1.45);
		allItems.add(item);

		item = new Item();
		item.setItemId("2");
		item.setItemName("Welt");
		item.setItemPrice(2.45);
		allItems.add(item);

		return Response.status(200).entity(allItems).build();
	}

	private User validUser(String username, String password) {
		User user = null;
		
		switch (username.length() % 3) {
		case 0:
			return user;
		case 1:
			user = new User();
			user.set_id("1");
			user.setFirm("SSC");
			user.setUsername("Andreas");
			user.setRolesList(Arrays.asList("amdin", "user"));
			return user;
		case 2:
			user = new User();
			user.set_id("2");
			user.setFirm("SSC");
			user.setUsername("Hugo");
			user.setRolesList(Arrays.asList("user"));
			return user;
		default:
			return null;
		}
	}
}
