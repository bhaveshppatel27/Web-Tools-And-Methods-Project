package com.cinema.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.cinema.exception.Excep;
import com.cinema.pojo.Customer;




public class CustomerDAO extends DAO {

    public CustomerDAO() {
    }

    public Customer get(String username)
            throws Excep {
        try {
            begin();
            Query q = getSession().createQuery("from Customer where username = :username");
            q.setString("username", username);
            Customer customer = (Customer) q.uniqueResult();
            commit();
            return customer;
        } catch (HibernateException e) {
            rollback();
            //throw new Excep("Could not get user " + username, e);
            return null;
        }
        
    }
    
    public Customer getById(String id)
            throws Excep {
        try {
            begin();
            Query q = getSession().createQuery("from Customer where email = :id");
            q.setCacheable(true);
            q.setString("id", id);
            Customer customer = (Customer) q.uniqueResult();
            commit();
            return customer;
        } catch (HibernateException e) {
            rollback();
            //throw new Excep("Could not get user " + username, e);
            return null;
        }
        
    }

    public Customer create(Customer c)
            throws Excep {
        try {
            begin();
            System.out.println("inside DAO");
            
            getSession().save(c);
            
            commit();
            return c;
        } catch (HibernateException e) {
            rollback();
            
            throw new Excep("Exception while creating user: " + e.getMessage());
        }
        
    }
    
    public void update		(Customer c) throws Excep {
        try {
            begin();
            getSession().update(c);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new Excep("Could not save the customer", e);
        }
    }

    public void delete(Customer c)
            throws Excep {
        try {
            begin();
            getSession().delete(c);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new Excep("Could not delete user " + c.getFirstName() + " " + c.getLastName(), e);
        }
        
    }
    
    public List list() throws Excep {
        try {
            begin();
            Query q = getSession().createQuery("from Customer");
            q.setCacheable(true);
            List list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new Excep("Could not list the categories", e);
        }
        
    }


}