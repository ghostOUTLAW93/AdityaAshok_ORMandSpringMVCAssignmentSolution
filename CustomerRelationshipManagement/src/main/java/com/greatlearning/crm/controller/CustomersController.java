package com.greatlearning.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.crm.entity.Customer;
import com.greatlearning.crm.service.CustomerService;

@Controller
@RequestMapping("/customers")
public class CustomersController {
	@Autowired
	private CustomerService customerService;

	@RequestMapping("/list")
	public String showCustomers(Model model)
	{
		List<Customer> customers=customerService.findAll();
		model.addAttribute("customers", customers);
		return "list-customers";
	}
	@RequestMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int id){
		customerService.deleteById(id);
		return "redirect:/customers/list";
	}
	@RequestMapping("/update")
	public String updateCustomer(@RequestParam("customerId") int id,Model model)
	{
		Customer customer=customerService.findById(id);
		model.addAttribute("customer",customer);
		return "customer-form";

	}

	@RequestMapping("/save")
	public String saveCustomer(@RequestParam("id") int id,
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("email") String email)
	{
		Customer customer;
		if(id==0)
		{
			customer=new Customer(firstName,lastName,email);
		}
		else {
			customer=customerService.findById(id);
			customer.setFirstName(firstName);
			customer.setLastName(lastName);
			customer.setEmail(email);
		}
		customerService.save(customer);
		return "redirect:/customers/list";
	}

	@RequestMapping("/showFormForAdd")
	public String addCustomer(Model model)
	{
		Customer customer=new Customer();
		model.addAttribute("customer",customer);
		return "customer-form";
	}

}
