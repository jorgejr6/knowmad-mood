package com.example.service;

import com.example.model.Book;
import com.example.model.BookDate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FilterBooks {

    public static Optional<BookDate> filter(String filter, List<Book> books) {

        //display books without publication date
        System.out.println("1. Books without publication date:");
        books.stream()
                .filter(book -> existsPublicationTimestamp(book.getPublicationTimestamp()))
                .forEach(b ->{
            System.out.println(b.getTitle()); //only show the title, I don't know if I have to show all book information
        });

        //filter book with the most recent publication date
        System.out.println("2. Book filter with the most recent publication date:");
        Optional<Book> optBook = books.stream()
                .filter(book -> book.getTitle().contains(filter) && book.getSummary().contains(filter) && book.getAuthor().getBio().contains(filter))
                .sorted(Comparator.comparing(Book::getPublicationTimestamp,Comparator.nullsLast(Comparator.reverseOrder())))
                .findFirst();
        Optional<BookDate> bookDate = optBook.isPresent() ? Optional.of(new BookDate(optBook.get())) : Optional.empty();
        if(bookDate.isPresent()) {
            BookDate b = bookDate.get();
            System.out.println("Filter book: " + b.getTitle() + " with publication date " + b.getTimestamp());
        } else {
            System.out.println("There aren't results for the filter");
        }
        //bookDate.ifPresent(b -> System.out.println("Filter book: " + b.getTitle() + " with publication date: " + b.getTimestamp()));

        //group by publication date and sort by bio length
        System.out.println("3. Books order list:");
        List<Book> orderBooks = new ArrayList<>();
        books.stream()
                .collect(Collectors.groupingBy(book -> existsPublicationTimestamp(book.getPublicationTimestamp()) ? "NULL": "NOTNULL"))
                .values().forEach(collection -> {
                    orderBooks.addAll(collection.stream().sorted(Comparator.comparing(b -> b.getAuthor().getBio().length())).toList());
                });
        orderBooks.forEach(b -> System.out.println(b.getTitle()));

        return bookDate;
    }

    private static boolean existsPublicationTimestamp(Long publicationTimestamp) {
        return publicationTimestamp == null || publicationTimestamp.toString().isBlank();
    }

}
