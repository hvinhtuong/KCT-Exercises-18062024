package com.example.bookmanager.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookModel {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private int sold;
    private LocalDate entryDate;
}
