package com.cinema.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.cinema.exception.Excep;
import com.cinema.pojo.ShowTimings;


public class ShowTimingsDAO extends DAO{

	public ShowTimingsDAO() {
    }

	public ShowTimings get(int id)
            throws Excep {
        try {
            begin();
            Query q = getSession().createQuery("from ShowTimings where showtimeid = :id");
            q.setInteger("id", id);
            ShowTimings showTimings = (ShowTimings) q.uniqueResult();
            commit();
            
            return showTimings;
        } catch (HibernateException e) {
            rollback();
            
            return null;
        }
        
    }
	
	public List getShowTimeByAudi(int audiid)
            throws Excep {
        try {
            begin();
            Query q = getSession().createQuery("from ShowTimings where audi = :id");
            q.setInteger("id", audiid);
            List list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            
            return null;
        }
        
    }
	
	public List getShowTimeByMovieId(int movieid)
            throws Excep {
        try {
            begin();
            Query q = getSession().createQuery("from ShowTimings where movie = :id");
            q.setInteger("id", movieid);
            List list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            
            return null;
        }
        
    }
	
	public ShowTimings getShowTimingsByName(String name)
            throws Excep {
        try {
            begin();
            Query q = getSession().createQuery("from ShowTimings where name = :name");
            q.setString("name", name);
            ShowTimings showTimings = (ShowTimings) q.uniqueResult();
            commit();
            return showTimings;
        } catch (HibernateException e) {
            rollback();
            
            return null;
        }
        
    }

    public ShowTimings create(ShowTimings t)
            throws Excep {
        try {
            begin();
            System.out.println("inside DAO");
            
            getSession().saveOrUpdate(t);
            
            commit();
            close();
            return t;
        } catch (HibernateException e) {
            rollback();
            
            throw new Excep("Exception while creating ShowTimings: " + e.getMessage());
        }
        finally{
        	close();
        }
        
    }
    
    public ShowTimings update(ShowTimings t)
            throws Excep {
        try {
            begin();
            System.out.println("inside DAO");
            
            getSession().update(t);
            
            commit();
            close();
            return t;
        } catch (HibernateException e) {
            rollback();
            
            throw new Excep("Exception while creating ShowTimings: " + e.getMessage());
        }
        finally{
        	close();
        }
        
    }


    public void delete(ShowTimings t)
            throws Excep {
        try {
            begin();
            getSession().delete(t);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new Excep("Could not delete ShowTimings " +  e);
        }
        
    }
    
    public List list() throws Excep {
        try {
            begin();
            Query q = getSession().createQuery("from ShowTimings");
            List list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new Excep("Could not list the ShowTimings", e);
        }
        
    }
	
}
