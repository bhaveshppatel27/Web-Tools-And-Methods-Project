package com.cinema.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.cinema.exception.Excep;
import com.cinema.pojo.Location;



public class LocationDAO extends DAO {

    public LocationDAO() {
    }

    public Location let(int id)
            throws Excep {
        try {
            begin();
            Query q = getSession().createQuery("from Location where locationid = :id");
            q.setInteger("id", id);
            Location l = (Location) q.uniqueResult();
            commit();
            return l;
        } catch (HibernateException e) {
            rollback();
            
            return null;
        }
        
    }
    
    public Location getByLocationName(String name)
            throws Excep {
        try {
            begin();
            Query q = getSession().createQuery("from Location where locate = :name");
            q.setString("name",name);
            Location l = (Location) q.uniqueResult();
            commit();
            return l;
        } catch (HibernateException e) {
            rollback();
            
            return null;
        }
       
    }

    public Location create(Location l)
            throws Excep {
        try {
            begin();
            System.out.println("inside DAO");
            
            getSession().saveOrUpdate(l);
            
            commit();
            return l;
        } catch (HibernateException e) {
            rollback();
            
            throw new Excep("Exception while creating Location: " + e.getMessage());
        }
        
    }

    public void delete(Location l)
            throws Excep {
        try {
            begin();
            getSession().delete(l);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new Excep("Could not delete Location " + e);
        }
        
    }
    
    public List list() throws Excep {
        try {
            begin();
            Query q = getSession().createQuery("from Location");
            List list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new Excep("Could not list the Location", e);
        }
        
    }
}