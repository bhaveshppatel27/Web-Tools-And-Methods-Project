package com.cinema.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.cinema.exception.Excep;
import com.cinema.pojo.TheatreAdmin;




public class TheatreAdminDAO extends DAO {

    public TheatreAdminDAO() {
    }

    public TheatreAdmin get(String username)
            throws Excep {
        try {
            begin();
            Query q = getSession().createQuery("from TheatreAdmin where username = :username");
            q.setString("username", username);
            TheatreAdmin theatreAdmin = (TheatreAdmin) q.uniqueResult();
            commit();
            return theatreAdmin;
        } catch (HibernateException e) {
            rollback();
            //throw new Excep("Could not get user " + username, e);
            return null;
        }
        
    }
    
    public TheatreAdmin getById(int id)
            throws Excep {
        try {
            begin();
            Query q = getSession().createQuery("from TheatreAdmin where theatreadminid = :id");
            q.setInteger("id", id);
            TheatreAdmin theatreAdmin = (TheatreAdmin) q.uniqueResult();
            commit();
            return theatreAdmin;
        } catch (HibernateException e) {
            rollback();
            //throw new Excep("Could not get user " + username, e);
            return null;
        }
        
    }

    public TheatreAdmin create(TheatreAdmin theatreAdmin)
            throws Excep {
        try {
            begin();
            System.out.println("inside DAO");
            
            TheatreAdmin theatreAdmin1 = (TheatreAdmin) getSession().save(theatreAdmin);
            
            commit();
            return theatreAdmin1;
        } catch (HibernateException e) {
            rollback();
            
            throw new Excep("Exception while creating user: " + e.getMessage());
        }
        
    }
    
    public void update		(TheatreAdmin c) throws Excep {
        try {
            begin();
            getSession().update(c);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new Excep("Could not save the TheatreAdmin", e);
        }
    }

    public void delete(TheatreAdmin c)
            throws Excep {
        try {
            begin();
            getSession().delete(c);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new Excep("Could not delete TheatreAdmin " +  e);
        }
        
    }
    
    public List list() throws Excep {
        try {
            begin();
            Query q = getSession().createQuery("from TheatreAdmin");
            List list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new Excep("Could not list the categories", e);
        }
        
    }


}