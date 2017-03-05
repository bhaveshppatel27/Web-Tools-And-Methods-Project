package com.cinema.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import com.cinema.exception.Excep;
import com.cinema.pojo.Budget;
import com.cinema.pojo.Movie;


public class MovieDAO extends DAO{

	public MovieDAO() {
	}

	public Movie get(String moviename)
			throws Excep {
		try {
			begin();
			Query q = getSession().createQuery("from Movie where name = :moviename");
			q.setString("moviename", moviename);
			Movie Movie = (Movie) q.uniqueResult();
			commit();
			return Movie;
		} catch (HibernateException e) {
			rollback();
			//throw new Excep("Could not get user " + moviename, e);
			return null;
		}
		
	}

	public List getMoviesByCategory(String category)
			throws Excep {
		try {
			begin();
			Query q = getSession().createQuery("from Movie where category = :category");
			q.setString("category", category);
			List list = q.list();
			commit();
			return list;
		} catch (HibernateException e) {
			rollback();

			return null;
		}
		
	}

	public List getMoviesByCategoryAndPage(String category,int count)
			throws Excep {
		try {
			begin();

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			Date date = new Date();
			System.out.println(dateFormat.format(date));
			SQLQuery q = null;
			String s = "SELECT * FROM cinema.showtimings as st inner join cinema.movie as m on m.movieid = st.movie  where category = :category and date > :date group by st.movie LIMIT "+8+" OFFSET "+count;
			if(category.equalsIgnoreCase("Coming Soon")){
				 q = getSession().createSQLQuery("Select * from Movie where category = :category LIMIT "+8+" OFFSET "+count);
			}else{
				 q = getSession().createSQLQuery(s);
				 q.setDate("date", date);
			}
			

			q.setParameter("category", category);
			
			q.addEntity(Movie.class);
			List list =  q.list();
			commit();
			return list;
		} catch (HibernateException e) {
			rollback();

			return null;
		}
		
	}
	
	public List getAllMoviesByName(String moviename,int count)
			throws Excep {
		try {
			begin();

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			Date date = new Date();
			System.out.println(dateFormat.format(date));
			SQLQuery q = null;
			String s = "SELECT * FROM cinema.showtimings as st inner join cinema.movie as m on m.movieid = st.movie where title LIKE :title and date > :date group by st.movie";
			q = getSession().createSQLQuery(s);
			q.setDate("date", date);

			q.setParameter("title", "%"+moviename+"%");
			
			q.addEntity(Movie.class);
			List list =  q.list();
			commit();
			return list;
		} catch (HibernateException e) {
			rollback();

			return null;
		}
		
	}

	public List getMoviesExcludingCategory(String category)
			throws Excep {
		try {
			begin();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			Date date = new Date();
			System.out.println(dateFormat.format(date));
			SQLQuery q = getSession().createSQLQuery("SELECT * FROM cinema.showtimings as st inner join cinema.movie as m on m.movieid = st.movie  where date > :date group by st.movie");
			//q.setParameter("category", category);
			q.addEntity(Movie.class);
			q.setDate("date", date);
			List list = q.list();
			commit();
			return list;
		} catch (HibernateException e) {
			rollback();

			return null;
		}
		
	}
	
	public List getMoviesExcept(String category)
			throws Excep {
		try {
			begin();

			SQLQuery q = getSession().createSQLQuery("SELECT * from Movie where category <> :category");
			q.setParameter("category", category);
			q.addEntity(Movie.class);
			List list = q.list();
			commit();
			return list;
		} catch (HibernateException e) {
			rollback();

			return null;
		}
		
	}

	public Movie getMovieById(int id)
			throws Excep {
		try {
			begin();
			Query q = getSession().createQuery("from Movie where movieid = :id");
			q.setInteger("id", id);
			Movie Movie = (Movie) q.uniqueResult();
			commit();
			return Movie;
		} catch (HibernateException e) {
			rollback();
			//throw new Excep("Could not get user " + moviename, e);
			return null;
		}
		
	}

	public Movie create(Movie m)
			throws Excep {
		try {
			begin();
			System.out.println("inside DAO");
			m.getBudget().setMovie(m);
			getSession().saveOrUpdate(m);

			commit();
			return m;
		} catch (HibernateException e) {
			rollback();

			throw new Excep("Exception while creating Movie: " + e.getMessage());
		}
		
	}
	public Movie update(Movie m)
			throws Excep {
		try {
			begin();
			System.out.println("inside DAO");
			
			getSession().update(m);

			commit();
			return m;
		} catch (HibernateException e) {
			rollback();

			throw new Excep("Exception while creating Movie: " + e.getMessage());
		}
		
	}

	public void delete(Movie m)
			throws Excep {
		try {
			begin();
			getSession().delete(m);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new Excep("Could not delete movie " + m.getTitle(), e);
		}
		
	}

	public List list() throws Excep {
		try {
			begin();
			Query q = getSession().createQuery("from Movie");
			List list = q.list();
			commit();
			return list;
		} catch (HibernateException e) {
			rollback();
			throw new Excep("Could not list the Movies", e);
		}
		
	}

}
