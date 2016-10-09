package de.ssc.restjpa;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class DependencyBinder extends AbstractBinder {

	@Override
	protected void configure() {
		bind(BankScImpl.class).to(BankSc.class);
		bind(BankResourcesAdapter.class).to(BankResourcesAdapter.class);
	}

}
