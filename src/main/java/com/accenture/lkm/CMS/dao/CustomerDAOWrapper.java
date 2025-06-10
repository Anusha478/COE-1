package com.accenture.lkm.CMS.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.accenture.lkm.CMS.business.bean.Customer;
import com.accenture.lkm.CMS.entity.CustomerEntity;



@Repository
public class CustomerDAOWrapper {
	
	@Autowired
	CustomerDAO dao;
	
	public List<Customer>findAll(){
		
		List<CustomerEntity> entityList =(List<CustomerEntity>)dao.findAll();
		
		List<Customer> beanList= new ArrayList<Customer>();
		
		for(CustomerEntity c:entityList) {
			
			Customer cus = new Customer();
			BeanUtils.copyProperties(c, cus);
			beanList.add(cus);
		}
		return beanList;
	}
	
	
	public Customer findOne(Integer id){ 
		Optional<CustomerEntity> entity =dao.findById(id);
		if(entity.isPresent()) {
		CustomerEntity ent=entity.get();
		Customer customer =new Customer();
		BeanUtils.copyProperties(ent,customer);
		
		    return customer;
		}
		else {
			return null;
		}
		
		
	}
	
	public Integer save(Customer customer){
		
		CustomerEntity entity = new CustomerEntity();
		BeanUtils.copyProperties(customer, entity);
		entity=dao.save(entity);
		int id = entity.getCustomerId();
		return id;	
	}
	
	public Customer update(Customer customer){
		CustomerEntity entity = new CustomerEntity();
		BeanUtils.copyProperties(customer, entity);

		entity=dao.save(entity);
		Customer customer2 =new Customer();
		BeanUtils.copyProperties(entity,customer2);
		
		return customer2;
	}
	
	public void delete(int id){
		dao.deleteById(id);
		
	}
	public List<Customer> findByDateGreaterThanEqualAndDateLessThan(Date lower,Date upper){
		//List<CustomerEntity> entityList =dao.findByBillingDateGreaterThanEqualAndBillingDateLessThan(lower, upper);
		List<CustomerEntity> entityList =dao.getCustomersWithinRange(lower, upper);
		List<Customer> beanList = new ArrayList<Customer>();
		for(CustomerEntity e:entityList) {
			Customer cust = new Customer();
			BeanUtils.copyProperties(e, cust);
			beanList.add(cust);
		}
		return beanList;
	}
	public List<Customer> findByCustomerNameStartingWithAndBillGreaterThanOrderByBillDesc(String pattern,Double bill){
		//List<CustomerEntity> entityList =dao.findByCustomerNameStartsWithAndBillGreaterThanOrderByBillDesc(pattern, bill);(remove "%" symbol for this)
		List<CustomerEntity> entityList =dao.getCustomersByNameStartsWithAndBillGreaterThan(pattern+"%", bill);
		List<Customer> beanList = new ArrayList<Customer>();
		for(CustomerEntity e:entityList) {
			Customer cust = new Customer();
			BeanUtils.copyProperties(e, cust);
			beanList.add(cust);
		}
		return beanList;
	}
	public List<Customer> findByCustomerNameEndingWithAndBillGreaterThanEqualOrderByBillDescThenOrderByCustomerIdAsc(String suffix,Double Bill){
		//List<CustomerEntity> entityList =dao.findByCustomerNameEndsWithAndBillGreaterThanEqualOrderByBillDescCustomerIdAsc(suffix, Bill);(remove "%" symbol for this)
		List<CustomerEntity> entityList =dao.getCustomersByNameEndsWithAndBillGreaterThanOrEqualAndOrderBy("%"+suffix,Bill);
		List<Customer> beanList = new ArrayList<Customer>();
		for(CustomerEntity e:entityList) {
			Customer cust = new Customer();
			BeanUtils.copyProperties(e, cust);
			beanList.add(cust);
		}
		return beanList;
	}

}
