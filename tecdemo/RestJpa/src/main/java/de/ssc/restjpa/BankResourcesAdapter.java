package de.ssc.restjpa;

import java.util.List;
import java.util.stream.Collectors;

import de.ssc.restjpa.entity.BankEntity;
import de.ssc.restjpa.model.Bank;

public class BankResourcesAdapter {

	public List<Bank> adapt(List<BankEntity> list) {
		return list.stream().map(be -> adapt(be)).collect(Collectors.toList());
//		ArrayList<Bank> resList = new ArrayList<Bank>();
//		for (BankEntity bankEntity : list) {
//
//			Bank bank = new Bank();
//			bank.setBankNumber(bankEntity.getBankNumber());
//			bank.setDescription(bankEntity.getDescription());
//			bank.setId(bankEntity.getId());
//			bank.setServerAdress(bankEntity.getServerAdress());
//			resList.add(adapt(bankEntity));
//		}
//		return resList;
	}
	
	public Bank adapt(BankEntity entity) {
		Bank bank = new Bank();

		bank.setBankNumber(entity.getBankNumber());
		bank.setDescription(entity.getDescription());
		bank.setId(entity.getId());
		bank.setServerAdress(entity.getServerAdress());
		
		return bank;
	}

}
