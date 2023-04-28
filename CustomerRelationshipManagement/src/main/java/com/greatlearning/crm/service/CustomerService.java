package com.greatlearning.crm.service;

import java.util.List;

import com.greatlearning.crm.entity.Customer;

public interface CustomerService {
	public List<Customer> findAll();
	public void deleteById(int id);
	public Customer findById(int id);
	public void save(Customer customer);
}
