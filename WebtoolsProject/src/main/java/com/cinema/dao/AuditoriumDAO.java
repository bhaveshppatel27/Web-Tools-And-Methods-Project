package com.cinema.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.cinema.exception.Excep;
import com.cinema.pojo.Budget;
import com.cinema.pojo.Genre;
import com.cinema.pojo.Movie;
import com.cinema.pojo.Auditorium;


public class AuditoriumDAO extends DAO{

	public AuditoriumDAO() {
    }

	public Auditorium get(int id)
            throws Excep {
        try {
            begin();
            Query q = getSession().createQuery("from Auditorium where audiid = :id");
            q.setInteger("id", id);
            Auditorium auditorium = (Auditorium) q.uniqueResult();
            commit();
            return auditorium;
        } catch (HibernateException e) {
            rollback();
            
            return null;
        }
       
    }
	
	public List getAudiByTheatre(int id)
            throws Excep {
        try {
            begin();
            Query q = getSession().createQuery("from Auditorium where theatre = :id");
            q.setInteger("id", id);
            List list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            
            return null;
        }
        
    }
	
	public Auditorium getAuditoriumByName(String name)
            throws Excep {
        try {
            begin();
            Query q = getSession().createQuery("from Auditorium where name = :name");
            q.setString("name", name);
            Auditorium auditorium = (Auditorium) q.uniqueResult();
            commit();
            return auditorium;
        } catch (HibernateException e) {
            rollback();
            
            return null;
        }
        
    }

    public Auditorium create(Auditorium a)
            throws Excep {
        try {
            begin();
            System.out.println("inside DAO");
            
            getSession().saveOrUpdate(a);
            
            commit();
            return a;
        } catch (HibernateException e) {
            rollback();
            
            throw new Excep("Exception while creating Auditorium: " + e.getMessage());
        }
        finally{
        	close();
        }
    }

    public void delete(Auditorium a)
            throws Excep {
        try {
            begin();
            getSession().delete(a);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new Excep("Could not delete Auditorium " + a.getName(), e);
        }
        
    }
    
    public List list() throws Excep {
        try {
            begin();
            Query q = getSession().createQuery("from Auditorium");
            List list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new Excep("Could not list the Auditorium", e);
        }
        
    }
	
}
