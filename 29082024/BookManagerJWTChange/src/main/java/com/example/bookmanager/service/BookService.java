package com.example.bookmanager.service;

import com.example.bookmanager.entity.BookEntity;
import com.example.bookmanager.model.soldReportModel;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookService extends BaseCRUD<BookEntity> {
    String sellBook(int id, int quantity);
    List<BookEntity> sortByPrice();
    List<BookEntity> sortBySold();
    List<soldReportModel> soldReport();
    Optional<BookEntity> findByIdOrName(int id, String name);
    List<BookEntity> findByDatesBetween(LocalDate start, LocalDate end);
    List<BookEntity> findBestSell();
}

