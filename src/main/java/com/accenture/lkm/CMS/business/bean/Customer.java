package com.accenture.lkm.CMS.business.bean;

import java.sql.Date;

public class Customer {
	
	
	public int customerId;
	public String customerName;
	public Date billingDate;
	public double bill;
	
	
	public Customer(int customerId, String customerName, Date billingDate, double bill) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.billingDate = billingDate;
		this.bill = bill;
	}
	
	public Customer() {
		
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Date getBillingDate() {
		return billingDate;
	}
	public void setBillingDate(Date billingDate) {
		this.billingDate = billingDate;
	}
	public double getBill() {
		return bill;
	}
	public void setBill(double bill) {
		this.bill = bill;
	}

	@Override
	public String toString() {
		return "Customer [CustomerId=" + customerId + ", CustomerName=" + customerName + ", billingDate=" + billingDate
				+ ", bill=" + bill + "]";
	}
	
	

}
