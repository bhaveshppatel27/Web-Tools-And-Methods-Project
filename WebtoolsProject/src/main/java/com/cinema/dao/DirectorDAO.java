package com.cinema.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.cinema.exception.Excep;
import com.cinema.pojo.Director;



public class DirectorDAO extends DAO {

    public DirectorDAO() {
    }

    public Director get(int id)
            throws Excep {
        try {
            begin();
            Query q = getSession().createQuery("from Director where personid = :id");
            q.setInteger("id", id);
            Director director = (Director) q.uniqueResult();
            commit();
            return director;
        } catch (HibernateException e) {
            rollback();
            
            return null;
        }
        
    }

    public Director create(Director d)
            throws Excep {
        try {
            begin();
            System.out.println("inside DAO");
            
            getSession().save(d);
            
            commit();
            return d;
        } catch (HibernateException e) {
            rollback();
            
            throw new Excep("Exception while creating Director: " + e.getMessage());
        }
        
    }

    public void delete(Director d)
            throws Excep {
        try {
            begin();
            getSession().delete(d);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new Excep("Could not delete Director " + d.getFirstName() + " " + d.getLastName(), e);
        }
        
    }
    
    public List list() throws Excep {
        try {
            begin();
            Query q = getSession().createQuery("from Director");
            q.setCacheable(true);
            List list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new Excep("Could not list the Director", e);
        }
        
    }
}