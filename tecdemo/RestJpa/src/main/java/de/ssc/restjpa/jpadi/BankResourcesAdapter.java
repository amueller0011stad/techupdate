package de.ssc.restjpa.jpadi;

import java.util.ArrayList;
import java.util.List;

import de.ssc.restjpa.entity.BankEntity;
import de.ssc.restjpa.model.Bank;

public class BankResourcesAdapter {

	public List<Bank> adapt(List<BankEntity> list) {
		ArrayList<Bank> resList = new ArrayList<Bank>();
		for (BankEntity bankEntity : list) {

			Bank bank = new Bank();
			bank.setBankNumber(bankEntity.getBankNumber());
			bank.setDescription(bankEntity.getDescription());
			bank.setId(bankEntity.getId());
			bank.setServerAdress(bankEntity.getServerAdress());
			resList.add(bank);
		}
		return resList;
	}
}
