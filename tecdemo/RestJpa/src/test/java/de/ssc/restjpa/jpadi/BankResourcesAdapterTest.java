package de.ssc.restjpa.jpadi;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.ssc.restjpa.entity.BankEntity;
import de.ssc.restjpa.jpadi.BankResourcesAdapter;
import de.ssc.restjpa.model.Bank;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class BankResourcesAdapterTest {

	@Test
	public void testAdapt() {
		BankResourcesAdapter testee = new BankResourcesAdapter();

		List<BankEntity> entities = new ArrayList<BankEntity>();
		BankEntity ent = new BankEntity();
		ent.setId(1);
		ent.setDescription("Hallo");
		entities.add(ent);
		
		List<Bank> adapt = testee.adapt(entities);
		assertThat(adapt.size(), is(1));
		assertThat(adapt.get(0).getDescription(), is("Hallo"));
	}

}
