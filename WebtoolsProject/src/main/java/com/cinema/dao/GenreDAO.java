package com.cinema.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.cinema.exception.Excep;
import com.cinema.pojo.Customer;
import com.cinema.pojo.Genre;



public class GenreDAO extends DAO {

    public GenreDAO() {
    }

    public Genre get(int id)
            throws Excep {
        try {
            begin();
            Query q = getSession().createQuery("from Genre where genreid = :id");
            
            q.setInteger("id", id);
            Genre genre = (Genre) q.uniqueResult();
            commit();
            return genre;
        } catch (HibernateException e) {
            rollback();
            
            return null;
        }
        
    }

    public Genre create(Genre g)
            throws Excep {
        try {
            begin();
            System.out.println("inside DAO");
            
            getSession().save(g);
            
            commit();
            return g;
        } catch (HibernateException e) {
            rollback();
            
            throw new Excep("Exception while creating Genre: " + e.getMessage());
        }
        
    }

    public void delete(Genre g)
            throws Excep {
        try {
            begin();
            getSession().delete(g);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new Excep("Could not delete Genre " + g.getName(), e);
        }
        
    }
    
    public List list() throws Excep {
        try {
        	System.out.println("Gstart");
            begin();
            Query q = getSession().createQuery("from Genre");
            q.setCacheable(true);
            List list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new Excep("Could not list the Genre", e);
        }
        finally{
        	System.out.println("Gstart");
        }
        
    }
}