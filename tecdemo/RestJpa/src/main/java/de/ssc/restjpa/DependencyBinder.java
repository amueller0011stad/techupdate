package de.ssc.restjpa;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import de.ssc.restjpa.jpadi.BankSc;
import de.ssc.restjpa.jpadi.BankScImpl;
import de.ssc.restjpa.jpadi.BankResourcesAdapter;

public class DependencyBinder extends AbstractBinder {

	@Override
	protected void configure() {
		bind(BankScImpl.class).to(BankSc.class);
		bind(BankResourcesAdapter.class).to(BankResourcesAdapter.class);
	}

}
