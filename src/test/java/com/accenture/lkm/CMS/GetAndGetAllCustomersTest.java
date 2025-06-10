package com.accenture.lkm.CMS;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.accenture.lkm.CMS.business.bean.Customer;
import com.accenture.lkm.CMS.utils.JSONUtils;

@SpringBootTest(classes = CmsApplication.class)
@Transactional
@WebAppConfiguration
public class GetAndGetAllCustomersTest {

	@Autowired
	WebApplicationContext webApplicationContext;
	
	MockMvc mockMvc;
	
	private Logger logger =LoggerFactory.getLogger(GetAndGetAllCustomersTest.class);
	@BeforeEach
	public void setup() {
		mockMvc=MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void getCustomerTest() throws Exception{
		String uri ="/getcustomer/1014";
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(uri);
		
		ResultActions result=mockMvc.perform(builder);
		MvcResult mvcResult=result.andReturn();
		
		String finalResult=mvcResult.getResponse().getContentAsString();
		int responseStatus=mvcResult.getResponse().getStatus();
		
		Customer customer =(Customer) JSONUtils.convertJsonToObject(finalResult,Customer.class);
		
		assertTrue(customer.getCustomerName().equals("Anu"));
		assertTrue(customer.getCustomerId()==1014);
		assertEquals(HttpStatus.OK.value(), responseStatus);
		
	}
	@Test
	public void getAllCustomersTest() throws Exception {
		
		String uri ="/getcustomers";
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(uri);
		
		ResultActions result=mockMvc.perform(builder);
		MvcResult mvcResult=result.andReturn();
		String finalResult=mvcResult.getResponse().getContentAsString();
		int responseStatus=mvcResult.getResponse().getStatus();
		
		
		logger.info(finalResult);
		
		List<Customer> allCustomers = JSONUtils.convertJsonToObject2(finalResult,List.class);
		assertTrue(allCustomers!=null);
		assertEquals(HttpStatus.OK.value(),responseStatus);
	
		
		
		
	}
	@Test
	public void InvalidGetCustomerTest() throws Exception{
		String uri ="/getcustomer/1011";
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(uri);
		
		ResultActions result=mockMvc.perform(builder);
		MvcResult mvcResult=result.andReturn();
		
		String finalResult=mvcResult.getResponse().getContentAsString();
		int responseStatus=mvcResult.getResponse().getStatus();
		
		
		assertEquals(HttpStatus.NOT_FOUND.value(), responseStatus);
		
	}
}
