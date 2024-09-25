package com.example;

import com.example.service.FilterBooks;
import com.example.model.Book;
import com.example.model.BookDate;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = Book.class.getResourceAsStream("/books.json");
            Book[] testObj = mapper.readValue(is, Book[].class);

            FilterBooks.filter(args[0], Arrays.stream(testObj).toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}