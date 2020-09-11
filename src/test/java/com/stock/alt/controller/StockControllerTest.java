package com.stock.alt.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.alt.model.Stock;
import com.stock.alt.service.StockService;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class StockControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StockService stockService;

	@Test
	public void shouldAuthenticated() throws Exception {

		this.mockMvc.perform(get("/stockQuotes").with(httpBasic("admin", "admin"))).andExpect(authenticated());
	}

	@Test
	public void shouldNotAuthenticated() throws Exception {

		this.mockMvc.perform(get("/stockQuotes").with(httpBasic("user", "user"))).andExpect(status().isUnauthorized());
	}

	@Test
	public void shouldReturnStockList() throws Exception {

		Stock stock = new Stock();
		stock.setStockQuote("HCL");
		stock.setDate(java.time.LocalDate.now().toString());
		System.out.println(java.time.LocalDate.now());
		stock.setCompanyName("HCL Tech");
		stock.setPrice(200.20989);
		stock.setCurrency("INR");

		given(stockService.getAllStock()).willReturn(
				new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(Arrays.asList(stock)));

		String expected = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(Arrays.asList(stock));

		MvcResult result = this.mockMvc.perform(get("/stockQuotes").with(httpBasic("admin", "admin"))).andReturn();

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void shouldReturnCreated() throws Exception {

		Stock stock = new Stock();
		stock.setStockQuote("HCL");
		stock.setDate(java.time.LocalDate.now().toString());
		System.out.println(java.time.LocalDate.now());
		stock.setCompanyName("HCL Tech");
		stock.setPrice(200.20989);
		stock.setCurrency("INR");

		Mockito.when(stockService.addstock(stock)).thenReturn(stock);

		this.mockMvc.perform(post("/stockQuote").contentType(MediaType.APPLICATION_JSON).content(
				"{\"stockQuote\":\"IBM5\",\"companyName\":\"IBM\",\"date\":\"2020-09-10\",\"price\":2121.454545,\"currency\":\"INR\"}")
				.accept(MediaType.APPLICATION_JSON).with(httpBasic("admin", "admin"))).andExpect(status().isCreated());

	}
}
