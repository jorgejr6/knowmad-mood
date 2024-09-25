package com.example.model;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    private String name;
    private String firstSurname;
    @NonNull
    private String bio;
}
