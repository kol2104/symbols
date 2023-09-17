package ru.t1consulting.symbols;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.t1consulting.symbols.controller.StringController;
import ru.t1consulting.symbols.service.StringService;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class StringControllerTest {
    @Mock
    private StringService stringService;
    @InjectMocks
    private StringController stringController;

    @Test
    void handleGetSymbolsInString_WhenStringIsValid_ReturnValidResponseEntity() {
        List<Map.Entry<Character, Integer>> list = new ArrayList<>();
        list.add(new AbstractMap.SimpleEntry<>('a', 2));
        list.add(new AbstractMap.SimpleEntry<>('b', 1));
        String string = "aab";
        Mockito.doReturn(list).when(stringService).getSymbolsInString(string);

        ResponseEntity<List<Map.Entry<Character, Integer>>> response = stringController.getSymbolsInString(string);

        Mockito.verify(stringService).getSymbolsInString(string);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(list, response.getBody());
    }

}
