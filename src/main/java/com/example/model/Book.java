package com.example.model;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private int id;
    @NonNull
    private String title;
    private Long publicationTimestamp;
    private int pages;
    @NonNull
    private String summary;
    @NonNull
    private Author author;
}
