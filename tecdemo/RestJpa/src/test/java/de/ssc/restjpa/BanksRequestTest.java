package de.ssc.restjpa;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.Ignore;
import org.junit.Test;

import de.ssc.restjpa.model.Bank;

/**
 * Testklasse um den Servicecall mit create und list auszuprobieren.
 * 
 * @author ufreise
 *
 */
public class BanksRequestTest
{
	// AJM Das ist kein Test der von Gradle beim bauen oder vom Jenkins ausgefuehrt werden sollte. Deshalb erst mal auf ignore.
	@Ignore @Test
	public void testServiceCall()
	{
		listBanks();
		createBank();
		listBanks();
	}
	
	private void listBanks()
	{
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonFeature.class);
		
		Client client = ClientBuilder.newClient(clientConfig);

		WebTarget webTarget = client.target("http://localhost:8080/corebanking/corebanking/banks/list");

		Builder request = webTarget.request();
		request.header("Content-type", MediaType.APPLICATION_JSON);

		List<Bank> banks = request.buildGet().invoke(new GenericType<List<Bank>>() {});
		
		System.out.println("Found " + banks.size() + " banks");
		for (Bank bank : banks) {
			System.out.println("  " + bank.getId() + ", " + bank.getBankNumber() + ", " + bank.getDescription() + ", " + bank.getServerAdress());
		}
	}
	
	private void createBank()
	{
		Bank bank = new Bank();
		bank.setBankNumber(4713);
		bank.setDescription("Deutsche Kartoffelbank");
		bank.setServerAdress("http://dkb.com/");
		
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonFeature.class);
		
		Client client = ClientBuilder.newClient(clientConfig);

		Builder request = client.target("http://localhost:8080/corebanking/corebanking/banks/create").request();
		request.header("Content-type", MediaType.APPLICATION_JSON);
		Bank createdBank = request.buildPut(Entity.json(bank)).invoke(new GenericType<Bank>() {});

		System.out.println("Bank with id " + createdBank.getId() + " created");
	}
	
	public static void main(String[] args) {
		new BanksRequestTest().testServiceCall();
	}
}
