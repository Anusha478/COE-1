package com.accenture.lkm.CMS.service;


import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.lkm.CMS.business.bean.Customer;
import com.accenture.lkm.CMS.dao.CustomerDAOWrapper;



@Service
public class Customerservice {
	
	@Autowired
	CustomerDAOWrapper daoWrapper;
	
	public List<Customer>findAll(){
		return daoWrapper.findAll();
	}
	
	
	public Customer findOne(Integer id){ 
		
		    return daoWrapper.findOne(id);
	}
	
	public Integer save(Customer customer){
		return daoWrapper.save(customer);	
	}
	
	public Customer update(Customer customer){
		return daoWrapper.update(customer);
	}
	
	public void delete(int id){
		
		daoWrapper.delete(id);
		
	}
	public List<Customer> findByDateGreaterThanEqualAndDateLessThan(Date lower,Date upper){
		return daoWrapper.findByDateGreaterThanEqualAndDateLessThan(lower, upper);
	}
	public List<Customer> findByCustomerNameStartingWithAndBillGreaterThanOrderByBillDesc(String pattern,Double bill){
		return daoWrapper.findByCustomerNameStartingWithAndBillGreaterThanOrderByBillDesc(pattern, bill);
	}
	public List<Customer> findByCustomerNameEndingWithAndBillGreaterThanEqualOrderByBillDescThenOrderByCustomerIdAsc(String suffix,Double Bill){
		return daoWrapper.findByCustomerNameEndingWithAndBillGreaterThanEqualOrderByBillDescThenOrderByCustomerIdAsc(suffix, Bill);
	}
}
