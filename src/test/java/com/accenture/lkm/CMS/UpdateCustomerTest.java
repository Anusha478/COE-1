package com.accenture.lkm.CMS;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
public class UpdateCustomerTest {
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	MockMvc mockMvc;
	
	@BeforeEach
	public void setup() {
		
		mockMvc=MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
	}
	@Test
	public void updateCustomerTest() throws Exception {
		
		String uri = "/updatecustomer";
		Customer customer = new Customer(1014,"Lalitha",Date.valueOf(LocalDate.now()),80000);
		
		String contentData = JSONUtils.convertObjectToJson(customer);
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put(uri)
				.accept(MediaType.APPLICATION_JSON)
				.content(contentData)
				.contentType(MediaType.APPLICATION_JSON);
		
		ResultActions result = mockMvc.perform(builder);
		MvcResult mvcResult=result.andReturn();
		
		String finalResult =mvcResult.getResponse().getContentAsString();
		int responseStatus=mvcResult.getResponse().getStatus();
		System.out.println(finalResult);
		assertTrue(finalResult!=null);
		assertEquals(HttpStatus.OK.value(),responseStatus);
	
		
	}
	@Test
	public void invalidUpdateCustomerTest() throws Exception {
			
			String uri = "/updatecustomer";
			Customer customer = new Customer(1011,"Lalitha",Date.valueOf(LocalDate.now()),80000);
			
			String contentData = JSONUtils.convertObjectToJson(customer);
			
			MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put(uri)
					.accept(MediaType.APPLICATION_JSON)
					.content(contentData)
					.contentType(MediaType.APPLICATION_JSON);
			
			ResultActions result = mockMvc.perform(builder);
			MvcResult mvcResult=result.andReturn();
			
			String finalResult =mvcResult.getResponse().getContentAsString();
			int responseStatus=mvcResult.getResponse().getStatus();
			System.out.println(finalResult);
			assertTrue(finalResult!=null);
			assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(),responseStatus);
		
			
		}

	
	
}
