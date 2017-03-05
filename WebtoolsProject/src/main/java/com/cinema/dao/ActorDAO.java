package com.cinema.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.cinema.exception.Excep;
import com.cinema.pojo.Actor;



public class ActorDAO extends DAO {

    public ActorDAO() {
    }

    public Actor get(int id)
            throws Excep {
        try {
            begin();
            Query q = getSession().createQuery("from Actor where personid = :id");
            q.setInteger("id", id);
            Actor actor = (Actor) q.uniqueResult();
            commit();
            return actor;
        } catch (HibernateException e) {
            rollback();
            
            return null;
        }
        
        
    }

    public Actor create(Actor a)
            throws Excep {
        try {
            begin();
            System.out.println("inside DAO");
            
            getSession().save(a);
            
            commit();
            return a;
        } catch (HibernateException e) {
            rollback();
            
            throw new Excep("Exception while creating actor: " + e.getMessage());
        }
        
    }

    public void delete(Actor a)
            throws Excep {
        try {
            begin();
            getSession().delete(a);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new Excep("Could not delete actor " + a.getFirstName() + " " + a.getLastName(), e);
        }
        
    }
    
    public List list() throws Excep {
        try {
        	System.out.println("Astart");
            begin();
            Query q = getSession().createQuery("from Actor");
            q.setCacheable(true);
            List list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new Excep("Could not list the actors", e);
        }
        finally{
        	System.out.println("Astart");
        }
        
    }
}