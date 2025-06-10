package com.accenture.lkm.CMS;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

@Transactional
@SpringBootTest(classes = CmsApplication.class)
@WebAppConfiguration
public class DeteleCustomerTest {
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	MockMvc mockMvc;
	
	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void deleteCustomerTest() throws Exception {
		String uri = "/deletecustomer/1014";
		MockHttpServletRequestBuilder builder=MockMvcRequestBuilders.delete(uri);
		
		ResultActions result = mockMvc.perform(builder);
		MvcResult mvcResult = result.andReturn();
		
		String finalResult=mvcResult.getResponse().getContentAsString();
		int responseStatus=mvcResult.getResponse().getStatus();
		
		Customer customer = JSONUtils.convertJsonToObject2(finalResult, Customer.class);
		
		assertTrue(customer.getCustomerName().equals("Anu"));
		assertEquals(HttpStatus.OK.value(), responseStatus);
		
		
	}
	
	@Test
	public void invalidDeleteCustomerTest() throws Exception {
		String uri = "/deletecustomer/1011";
		MockHttpServletRequestBuilder builder=MockMvcRequestBuilders.delete(uri);
		
		ResultActions result = mockMvc.perform(builder);
		MvcResult mvcResult = result.andReturn();
		
		String finalResult=mvcResult.getResponse().getContentAsString();
		int responseStatus=mvcResult.getResponse().getStatus();
		
		
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseStatus);
		
		
	}

}
