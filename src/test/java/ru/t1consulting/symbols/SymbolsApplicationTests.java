package ru.t1consulting.symbols;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.t1consulting.symbols.service.StringService;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class SymbolsApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Mock
	StringService stringService;

	@Test
	void contextLoads() {
	}

	@Test
	void handleGetSymbolsInString_WhenStringInvalid_ReturnBadRequest() throws Exception {
		String string = "aAB-.";

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/parse?string=" + string))
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andReturn();

		assertEquals("String contains unknown symbols: " + string, result.getResponse().getContentAsString());
	}

	@Test
	void handleGetSymbolsInString_WhenStringIsValid_ReturnValidResponseEntity() throws Exception {
		List<Map.Entry<Character, Integer>> list = new ArrayList<>();
		list.add(new AbstractMap.SimpleEntry<>('a', 2));
		list.add(new AbstractMap.SimpleEntry<>('b', 1));
		String string = "aab";

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/parse?string=" + string))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		List<Map.Entry<Character, Integer>> resultList = new ObjectMapper()
				.readValue(result.getResponse().getContentAsString(),
						new TypeReference<List<Map.Entry<Character, Integer>>>(){});

		assertEquals(list, resultList);
	}

	@Test
	void handleGetSymbolsInStringStringIsNullReturnValidResponseEntity() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/parse"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		List<Map.Entry<Character, Integer>> resultList = new ObjectMapper()
				.readValue(result.getResponse().getContentAsString(),
						new TypeReference<List<Map.Entry<Character, Integer>>>(){});

		assertEquals(0, resultList.size());
	}
}
