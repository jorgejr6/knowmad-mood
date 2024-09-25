package com.example.model;

import lombok.Getter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@Getter
public class BookDate extends Book{

    public BookDate(Book book) {
        super(book.getId(), book.getTitle(), book.getPublicationTimestamp(), book.getPages(), book.getSummary(), book.getAuthor());

        Long publicationTimestamp = book.getPublicationTimestamp();
        if(publicationTimestamp != null) {
            Date d = new Date(publicationTimestamp*1000);
            DateFormat f = new SimpleDateFormat("MM-dd-yyyy");
            this.timestamp = f.format(d);
        }

    }

    private String timestamp;
}
