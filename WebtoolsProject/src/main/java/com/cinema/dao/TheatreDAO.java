package com.cinema.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.cinema.exception.Excep;
import com.cinema.pojo.Budget;
import com.cinema.pojo.Genre;
import com.cinema.pojo.Movie;
import com.cinema.pojo.Theatre;


public class TheatreDAO extends DAO{

	public TheatreDAO() {
    }

	public Theatre get(int id)
            throws Excep {
        try {
            begin();
            Query q = getSession().createQuery("from Theatre where theatreid = :id");
            q.setInteger("id", id);
            Theatre theatre = (Theatre) q.uniqueResult();
            commit();
            return theatre;
        } catch (HibernateException e) {
            rollback();
            
            return null;
        }
       
    }
	
	public Theatre getTheatreByName(String name)
            throws Excep {
        try {
            begin();
            Query q = getSession().createQuery("from Theatre where name = :name");
            q.setString("name", name);
            Theatre theatre = (Theatre) q.uniqueResult();
            commit();
            return theatre;
        } catch (HibernateException e) {
            rollback();
            
            return null;
        }
       
    }

    public Theatre create(Theatre t)
            throws Excep {
        try {
            begin();
            System.out.println("inside DAO");
            t.getTheatreAdmin().setRole(t.getName()+"_"+t.getLocate());
            t.getTheatreAdmin().setTheatre(t);
            getSession().saveOrUpdate(t);
            
            commit();
            return t;
        } catch (HibernateException e) {
            rollback();
            
            throw new Excep("Exception while creating Theatre: " + e.getMessage());
        }
      
    }

    public void delete(Theatre t)
            throws Excep {
        try {
            begin();
            getSession().delete(t);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new Excep("Could not delete Theatre " + t.getName(), e);
        }
        
    }
    
    public List list() throws Excep {
        try {
            begin();
            Query q = getSession().createQuery("from Theatre");
            List list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new Excep("Could not list the Theatre", e);
        }
      
    }
	
}
