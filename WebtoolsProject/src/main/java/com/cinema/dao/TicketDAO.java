package com.cinema.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.cinema.exception.Excep;
import com.cinema.pojo.Customer;
import com.cinema.pojo.Ticket;



public class TicketDAO extends DAO {

    public TicketDAO() {
    }

    public Ticket get(int id)
            throws Excep {
        try {
            begin();
            Query q = getSession().createQuery("from Ticket where ticketid = :id");
            q.setInteger("id", id);
            Ticket t = (Ticket) q.uniqueResult();
            commit();
            return t;
        } catch (HibernateException e) {
            rollback();
            
            return null;
        }
        
    }

    public Ticket create(Ticket t)
            throws Excep {
        try {
            begin();
            System.out.println("inside DAO");
            
            Ticket t1 = (Ticket) getSession().merge(t);
            
            commit();
            return t1;
        } catch (HibernateException e) {
            rollback();
            
            throw new Excep("Exception while creating Ticket: " + e.getMessage());
        }finally {
			close();
		}
        
    }

    public void delete(Ticket t)
            throws Excep {
        try {
            begin();
            getSession().delete(t);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new Excep("Could not delete Ticket " +  e);
        }
        
    }
    
    public List list() throws Excep {
        try {
            begin();
            Query q = getSession().createQuery("from Ticket");
            List list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new Excep("Could not list the Ticket", e);
        }
        
    }
}