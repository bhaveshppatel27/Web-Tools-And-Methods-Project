package com.cinema.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.cinema.exception.Excep;

import com.cinema.pojo.BankAccount;


public class BankAccountDAO extends DAO{

	public BankAccountDAO() {
	}

	public BankAccount get(int id)
			throws Excep {
		try {
			begin();
			Query q = getSession().createQuery("from BankAccount where accountid = :id");
			q.setCacheable(true);
			q.setInteger("id", id);
			BankAccount ba = (BankAccount) q.uniqueResult();
			commit();
			return ba;
		} catch (HibernateException e) {
			rollback();

			return null;
		}

	}



	public BankAccount create(BankAccount ba)
			throws Excep {
		try {
			begin();
			System.out.println("inside DAO");

			getSession().saveOrUpdate(ba);

			commit();
			return ba;
		} catch (HibernateException e) {
			rollback();

			throw new Excep("Exception while creating BankAccount: " + e.getMessage());
		}

	}

	public void delete(BankAccount ba)
			throws Excep {
		try {
			begin();
			getSession().delete(ba);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new Excep("Could not delete BankAccount " + e);
		}

	}

	public List list() throws Excep {
		try {
			begin();
			Query q = getSession().createQuery("from BankAccount");
			List list = q.list();
			commit();
			return list;
		} catch (HibernateException e) {
			rollback();
			throw new Excep("Could not list the BankAccount", e);
		}

	}

}
