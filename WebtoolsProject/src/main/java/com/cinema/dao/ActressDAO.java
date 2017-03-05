package com.cinema.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.cinema.exception.Excep;

import com.cinema.pojo.Actress;




public class ActressDAO extends DAO {

    public ActressDAO() {
    }

    public Actress get(int id)
            throws Excep {
        try {
            begin();
            Query q = getSession().createQuery("from Actress where personid = :id");
            q.setInteger("id", id);
            Actress actress = (Actress) q.uniqueResult();
            commit();
            return actress;
        } catch (HibernateException e) {
            rollback();

            return null;
        }
        
    }

    public Actress create(Actress a)
            throws Excep {
        try {
            begin();
            System.out.println("inside DAO");
            
            getSession().save(a);
            
            commit();
            return a;
        } catch (HibernateException e) {
            rollback();
            
            throw new Excep("Exception while creating actress: " + e.getMessage());
        }
        
    }

    public void delete(Actress a)
            throws Excep {
        try {
            begin();
            getSession().delete(a);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new Excep("Could not delete actress " + a.getFirstName() + " " + a.getLastName(), e);
        }
        
    }
    
    public List list() throws Excep {
        try {
        	System.out.println("Actstart");
            begin();
            Query q = getSession().createQuery("from Actress");
            q.setCacheable(true);
            List list = q.list();
            commit();
            
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new Excep("Could not list the actress", e);
        }
        finally {
			System.out.println("Actstart");
		}
    }
}