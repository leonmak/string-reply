package com.beta.replyservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class RestServiceApplicationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testGivenEmptyMessageReturnEmptyMessage() throws Exception {
		mockMvc.perform(get("/reply"))
			.andExpect(status().isOk())
			.andExpect(content()
					.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.message", is("Message is empty")));
	}

	@Test
	public void testGivenMessageReturnSameMessage() throws Exception {
		mockMvc.perform(get("/reply/test"))
			.andExpect(status().isOk())
			.andExpect(content()
					.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(content().json("{\"message\": \"test\"}"));
	}

	@Test
	public void testGivenInvalidPathReturnNotFound() throws Exception {
		mockMvc.perform(get("/v2/reply/21kbzw9ru"))
			.andExpect(status().isNotFound());
	}

	@ParameterizedTest
	@CsvSource({
		"21, kbzw9ru, daf168567f92b1c464459087eaaefaf0",
		"12, kbzw9ru, 5a8973b3b1fafaeaadf10e195c6e1dd4",
		"22, kbzw9ru, e8501e64cf0a9fa45e3c25aa9e77ffd5",
		"11, kbzw9ru, kbzw9ru",
	})
	public void testGivenMessageWithCommandReturnData(String code, String msg, String val) throws Exception {
		mockMvc.perform(get("/v2/reply/{code}-{message}", code, msg))
			.andExpect(status().isOk())
			.andExpect(content()
				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.data", is(val)));
	}

	@ParameterizedTest
	@CsvSource({
		"123, test",
		"1, test",
		", test",
	})
	public void testGivenInvalidCodeLengthReturnBadRequest(String code, String msg) throws Exception {
		mockMvc.perform(get("/v2/reply/{code}-{message}", code, msg))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.message", is("Invalid input")));
	}


	@ParameterizedTest
	@CsvSource({
		"--, test",
		"20, test",
		"13, test",
	})	public void testGivenInvalidCodeValueReturnBadRequest(String code, String msg) throws Exception {
		mockMvc.perform(get("/v2/reply/{code}-{message}", code, msg))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.message", is("Invalid input")));
	}
}
