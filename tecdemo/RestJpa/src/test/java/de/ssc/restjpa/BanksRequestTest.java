package de.ssc.restjpa;

import static org.junit.Assert.assertNotNull;

import javax.ws.rs.core.MediaType;

import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

import de.ssc.restjpa.model.Bank;

/**
 * Testklasse um den Servicecall mit create und list auszuprobieren.
 * 
 * @author ufreise
 *
 */
public class BanksRequestTest 
{
	@Test
	public void testServiceCall()
	{
		// 1. Datensatz einfügen
		// Web Resource erzeugen
		WebResource webRes = Client.create().resource("http://localhost:8080/RestJpa");
		// JSon Objekt mit den einzufügenden Bankdaten erzeugen
		Bank bank = new Bank();
		bank.setBankNumber(4713);
		bank.setDescription("Deutsche Kartoffelbank");
		bank.setServerAdress("http://localhost:8081/RestJpa");
		// Service Aufruf und Datensatz in DB schreiben
		ClientResponse response = 
				webRes.path("corebanking").path("banks").path("create").type(MediaType.APPLICATION_JSON).put(ClientResponse.class, bank);
		assertNotNull(response.getEntity(Bank.class));
		// 2. Daten auslesen
		webRes = Client.create().resource("http://localhost:8080/RestJpa");
		Builder sb1 = webRes.path("corebanking").path("banks").path("list").accept(MediaType.APPLICATION_JSON);
		System.out.println(sb1.get(String.class));
	}
}
