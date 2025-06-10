package com.accenture.lkm.CMS.web.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.lkm.CMS.business.bean.Customer;
import com.accenture.lkm.CMS.service.Customerservice;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CustomerController {
	
	@Autowired
	Customerservice customerservice;
	
	@RequestMapping(value="addcustomer",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
		
		int id=customerservice.save(customer);
		customer.setCustomerId(id);
		 return new ResponseEntity<Customer>(customer,HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "getcustomers",method=RequestMethod.GET)
	public ResponseEntity<List<Customer>> getCustomers(){
		
		return new ResponseEntity<List<Customer>>(customerservice.findAll(),HttpStatus.OK);
	}
	
	@RequestMapping(value="getcustomer/{id}",method = RequestMethod.GET)
	public ResponseEntity<Customer> findById( @PathVariable("id") int id) {

			Customer customer	=customerservice.findOne(id);
			if(customer==null) {
				return new ResponseEntity<Customer>(customer,HttpStatus.NOT_FOUND);
			}
			else {
				return new ResponseEntity<Customer>(customer,HttpStatus.OK);
			}
	}
	
	@RequestMapping(value="updatecustomer",method = RequestMethod.PUT)
	public ResponseEntity<Customer>  updateCustomer(@RequestBody Customer customer) {
		if(customerservice.findOne(customer.getCustomerId())==null) {
			Customer cust=null;
			return new ResponseEntity<Customer>(cust,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		else {
		customer=customerservice.update(customer);
		return new ResponseEntity<Customer>(customer,HttpStatus.OK);
		}
		
		
	}
	@RequestMapping(value = "deletecustomer/{id}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") int id) {
		Customer customer=customerservice.findOne(id);
		if(customer==null) {
			Customer cust=null;
			return new ResponseEntity<Customer>(cust,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		else {
		customerservice.delete(id);
		 return new ResponseEntity<Customer>(customer,HttpStatus.OK);
		}
		
		
	}
	@RequestMapping(value ="getcustomersbyquery1/{sd}/{ed}")
	public ResponseEntity<List<Customer>> getcustomersbyquery1(
			@PathVariable("sd") Date startDate,@PathVariable("ed") Date endDate){
		
		List<Customer> customers=customerservice.findByDateGreaterThanEqualAndDateLessThan(startDate, endDate);
		if(customers.isEmpty()) {
			return new ResponseEntity<List<Customer>>(customers,HttpStatus.NOT_FOUND);
		}
		else {
			
		}
		return new ResponseEntity<List<Customer>>(customers,HttpStatus.OK);
		
	}
	
	@RequestMapping(value ="getcustomersbyquery2/{pre}/{bill}")
	public ResponseEntity<List<Customer>> getcustomersbyquery2(
			@PathVariable("pre") String prefix,@PathVariable("bill") Double bill){
		
		List<Customer> customers=customerservice.findByCustomerNameStartingWithAndBillGreaterThanOrderByBillDesc(prefix, bill);
		if(customers.isEmpty()) {
			return new ResponseEntity<List<Customer>>(customers,HttpStatus.NOT_FOUND);
		}
		else {
			
		}
		return new ResponseEntity<List<Customer>>(customers,HttpStatus.OK);
		
	}
	
	@RequestMapping(value ="getcustomersbyquery3/{suf}/{bill}")
	public ResponseEntity<List<Customer>> getcustomersbyquery3(
			@PathVariable("suf") String suffix,@PathVariable("bill") Double bill){
		
		List<Customer> customers=customerservice.findByCustomerNameEndingWithAndBillGreaterThanEqualOrderByBillDescThenOrderByCustomerIdAsc(suffix, bill);
		if(customers.isEmpty()) {
			return new ResponseEntity<List<Customer>>(customers,HttpStatus.NOT_FOUND);
		}
		else {
			
		}
		return new ResponseEntity<List<Customer>>(customers,HttpStatus.OK);
		
	}
	
	
	
	

}
