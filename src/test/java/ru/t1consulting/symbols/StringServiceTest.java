package ru.t1consulting.symbols;

import org.junit.jupiter.api.Test;
import ru.t1consulting.symbols.exception.StringFormatException;
import ru.t1consulting.symbols.service.StringService;
import ru.t1consulting.symbols.service.impl.StringServiceImpl;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringServiceTest {

    @Test
    void handleGetSymbolsInString_WhenStringIsValid_ReturnValidList() {
        StringService stringService = new StringServiceImpl();
        List<Map.Entry<Character, Integer>> list = new ArrayList<>();
        list.add(new AbstractMap.SimpleEntry<>('c', 5));
        list.add(new AbstractMap.SimpleEntry<>('a', 2));
        list.add(new AbstractMap.SimpleEntry<>('b', 1));
        String string = "abcccacc";

        List<Map.Entry<Character, Integer>> result = stringService.getSymbolsInString(string);

        assertNotNull(result);
        assertEquals(list.size(), result.size());
        assertEquals(list, result);
    }

    @Test
    void handleGetSymbolsInString_WhenStringInvalid_ThrowException() {
        StringService stringService = new StringServiceImpl();
        String string = "abccca.cc";

        StringFormatException exception = assertThrows(StringFormatException.class,
                () -> stringService.getSymbolsInString(string));

        assertEquals("String contains unknown symbols: " + string, exception.getMessage());
    }

    @Test
    void handleGetSymbolsInString_WhenStringIsNull_ReturnEmptyList() {
        StringService stringService = new StringServiceImpl();
        String string = null;

        List<Map.Entry<Character, Integer>> result = stringService.getSymbolsInString(string);

        assertNotNull(result);
        assertEquals(0, result.size());
    }

}
