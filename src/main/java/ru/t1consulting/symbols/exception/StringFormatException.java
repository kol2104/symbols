package ru.t1consulting.symbols.exception;

public class StringFormatException extends RuntimeException{
    public StringFormatException(String str) {
        super("String contains unknown symbols: " + str);
    }
}
