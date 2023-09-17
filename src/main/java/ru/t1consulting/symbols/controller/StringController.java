package ru.t1consulting.symbols.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.t1consulting.symbols.exception.StringFormatException;
import ru.t1consulting.symbols.service.StringService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/parse")
public class StringController {

    private StringService stringService;

    public StringController(StringService stringService) {
        this.stringService = stringService;
    }

    @GetMapping
    public ResponseEntity<List<Map.Entry<Character, Integer>>> getSymbolsInString(
            @RequestParam(value = "string", required = false) String string) {
        return new ResponseEntity<>(stringService.getSymbolsInString(string), HttpStatus.OK);
    }

    @ExceptionHandler({StringFormatException.class})
    public ResponseEntity handleException(Exception exception) {
        Object errorBody = exception.getMessage();
        return new ResponseEntity(errorBody, HttpStatus.BAD_REQUEST);
    }
}
