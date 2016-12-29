package de.ssc.restjpa;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.LoggerFactory;

import de.ssc.restjpa.entity.BankEntity;
import de.ssc.restjpa.model.Bank;

@Path("banks")
public class BanksRequest {

	@Inject BankSc mModel;
	@Inject BankResourcesAdapter mAdapter;

	@Path("create")
	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public Bank create(Bank input) {
		BankEntity entity = mModel.add(input.getBankNumber(), input.getDescription(), input.getServerAdress());
		return mAdapter.adapt(entity);
	}

	@Path("list")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Bank> list() {
		LoggerFactory.getLogger(this.getClass()).info("start - list()");

		List<BankEntity> list = mModel.list();
		List<Bank> resList = mAdapter.adapt(list);
		
		LoggerFactory.getLogger(this.getClass()).info("end - list()");
		return resList;
	}
	
//	@Path("{example}")
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<Bank> example(@PathParam("example") String example) {
//		List<BankEntity> list = mModel.addAndList(example);
//		List<Bank> resList = mAdapter.adapt(list);
//		return resList;
//	}

}
