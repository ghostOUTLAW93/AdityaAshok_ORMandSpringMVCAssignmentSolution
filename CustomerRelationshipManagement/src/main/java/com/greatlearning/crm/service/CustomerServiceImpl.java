package com.greatlearning.crm.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.greatlearning.crm.entity.Customer;

@Repository
public class CustomerServiceImpl implements CustomerService {

	private SessionFactory sessionFactory;

	// create session
	private Session session;

	@Autowired
	CustomerServiceImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}

	}

	@Override
	public List<Customer> findAll() {
		Transaction tx = session.beginTransaction();
		List<Customer> customers = session.createQuery("from Customer").list();

		tx.commit();

		return customers;
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		Transaction tx=session.beginTransaction();
		Customer customer=session.get(Customer.class,id);
		session.delete(customer);
		tx.commit();

	}

	@Override
	public Customer findById(int id) {
		Transaction tx=session.beginTransaction();
		Customer customer=session.get(Customer.class,id);
		tx.commit();
		return customer;
	}

	@Override
	public void save(Customer customer) {
		Transaction tx=session.beginTransaction();
		session.save(customer);
		tx.commit();

	}

}
