package ru.t1consulting.symbols.service;

import java.util.List;
import java.util.Map;

public interface StringService {
    List<Map.Entry<Character, Integer>> getSymbolsInString(String string);
}
