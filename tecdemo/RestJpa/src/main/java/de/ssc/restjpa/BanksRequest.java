package de.ssc.restjpa;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.ssc.restjpa.entity.BankEntity;
import de.ssc.restjpa.model.Bank;

@Path("banks")
public class BanksRequest {

	@Inject BankSc mModel;
	@Inject BankResourcesAdapter mAdapter;

//	@Path("{example}")
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<Bank> example(@PathParam("example") String example) {
//		List<BankEntity> list = mModel.addAndList(example);
//		List<Bank> resList = mAdapter.adapt(list);
//		return resList;
//	}

	@Path("list")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Bank> list() {
		List<BankEntity> list = mModel.list();
		List<Bank> resList = mAdapter.adapt(list);
		return resList;
	}
}
