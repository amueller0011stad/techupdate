package de.ssc.restjpa.jpadi;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import de.ssc.restjpa.entity.BankEntity;

public class BankScImpl implements BankSc {

	@Override
	public BankEntity add(int bankNumber, String description, String serverAdress) {

		EntityManager entityManager = Persistence.createEntityManagerFactory("REST_JPA").createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			
			BankEntity entity = new BankEntity();
			entity.setBankNumber(bankNumber);
			entity.setDescription(description);
			entity.setServerAdress(serverAdress);
			
			entityManager.persist(entity);
			return entity;
		} catch (Exception e) {
			Logger.getLogger(getClass()).error("Problem persisting", e);
			transaction.rollback();
			throw e;
		} finally {
			entityManager.clear();
			entityManager.close();
		}
	}

	@Override
	public List<BankEntity> list() {

		EntityManager entityManager = Persistence.createEntityManagerFactory("REST_JPA").createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		List<BankEntity> list = null;
		try {
			transaction.begin();
			TypedQuery<BankEntity> nq = entityManager.createNamedQuery("list", BankEntity.class);
			list = nq.getResultList();
			transaction.commit();
		} catch (Exception e) {
			Logger.getLogger(getClass()).error("Problem reading", e);
			transaction.rollback();
			throw e;
		} finally {
			entityManager.clear();
			entityManager.close();
		}
		return list;
	}

}
