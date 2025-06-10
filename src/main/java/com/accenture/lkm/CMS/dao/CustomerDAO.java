package com.accenture.lkm.CMS.dao;


import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.lkm.CMS.entity.CustomerEntity;



@Repository
@Transactional(transactionManager = "txManager")
public interface CustomerDAO extends CrudRepository<CustomerEntity, Integer>{
	
	
//public List<CustomerEntity> findByBillingDateGreaterThanEqualAndBillingDateLessThan(Date lower,Date upper);
//public List<CustomerEntity> findByCustomerNameStartsWithAndBillGreaterThanOrderByBillDesc(String pattern,Double bill);
//public List<CustomerEntity> findByCustomerNameEndsWithAndBillGreaterThanEqualOrderByBillDescCustomerIdAsc(String suffix,Double Bill);
    @Query("select c from CustomerEntity c where billingDate>= :startDate and billingDate< :endDate")
	public List<CustomerEntity> getCustomersWithinRange(@Param("startDate") Date startDate,@Param("endDate") Date endDate);
    @Query("select c from CustomerEntity c where customerName LIKE :pattern and bill > :bill ORDER BY bill DESC")
    public List<CustomerEntity> getCustomersByNameStartsWithAndBillGreaterThan(@Param("pattern") String pattern,@Param("bill") Double bill);
    @Query("select c from CustomerEntity c where customerName LIKE :suffix and bill>= :bill ORDER BY bill DESC,customerId ASC" )
    public List<CustomerEntity> getCustomersByNameEndsWithAndBillGreaterThanOrEqualAndOrderBy(@Param("suffix") String suffix,@Param("bill") Double bill);
}
