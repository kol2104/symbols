package ru.t1consulting.symbols.service.impl;

import org.springframework.stereotype.Service;
import ru.t1consulting.symbols.exception.StringFormatException;
import ru.t1consulting.symbols.service.StringService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StringServiceImpl implements StringService {
    /**
     * The method to parse string to list of symbols. The list
     * sorted in descending order of the number of occurring symbols
     * (if the number is equal, the symbols are sorted in ascending
     * order of the symbol code).
     * @param string to parse
     * @return sorted list of symbols
     * @throws StringFormatException if input strings contain something except a-z or A-Z symbols
     */
    public List<Map.Entry<Character, Integer>> getSymbolsInString(String string) {
        Map<Character, Integer> symbols = new HashMap<>();
        if (string == null || string.length() == 0) {
            return new ArrayList<>();
        }
        if (!string.matches("[a-zA-Z]*")) {
            throw new StringFormatException(string);
        }
        string.chars()
                .mapToObj(c -> (char)c)
                .forEach(c -> {
                    if (symbols.containsKey(c)) {
                        symbols.put(c, symbols.get(c) + 1);
                    } else {
                        symbols.put(c, 1);
                    }
                });

        return symbols
                .entrySet()
                .stream()
                .sorted((o1, o2) ->
                        !o2.getValue().equals(o1.getValue()) ?
                                o2.getValue() - o1.getValue() :
                                o1.getKey() - o2.getKey())
                .collect(Collectors.toList());
    }
}
