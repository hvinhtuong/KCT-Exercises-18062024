package com.example.bookmanager.service.impl;

import com.example.bookmanager.entity.BookEntity;
import com.example.bookmanager.exception.BookNotFoundException;
import com.example.bookmanager.model.soldReportModel;
import com.example.bookmanager.repository.BookRepository;
import com.example.bookmanager.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public BookEntity create(BookEntity bookEntity) {
        return bookRepository.save(bookEntity);
    }

    @Override
    public BookEntity update(BookEntity bookEntity) {
        return bookRepository.save(bookEntity);
    }

    @Override
    public boolean delete(BookEntity bookEntity) {
        Optional<BookEntity> bookID = bookRepository.findById(bookEntity.getId());
        if (bookID.isPresent()) {
            bookRepository.delete(bookEntity);
            return true;
        }
        return false;
    }

    @Override
    public Iterator<BookEntity> findAll() {
        return bookRepository.findAll().iterator();
    }

    @Override
    public Optional<BookEntity> findById(int id) {
        return Optional.ofNullable(bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Không tìm thấy sách với ID: " + id)));
    }

    @Override
    public String sellBook(int id, int quantity) {
        BookEntity bookEntity = bookRepository.findById(id).get();
        if (bookEntity.getQuantity() >= quantity) {
            bookEntity.setQuantity(bookEntity.getQuantity() - quantity);
            bookEntity.setSold(quantity);
            bookRepository.save(bookEntity);
            return "Da ban thanh cong " + quantity + " cuon sach " + bookEntity.getName();
        }
        return "Khong du so luong";
    }

    @Override
    public List<BookEntity> sortByPrice() {
        List<BookEntity> list = bookRepository.findAll();
        list.sort((b1, b2) -> Double.compare(b1.getPrice(), b2.getPrice()));
        return list;
    }

    @Override
    public List<BookEntity> sortBySold() {
        List<BookEntity> list = bookRepository.findAll();
        list.sort((b1, b2) -> Integer.compare(b1.getSold(), b2.getSold()));
        return list;
    }

    @Override
    public List<soldReportModel> soldReport() {
        List<BookEntity> books = bookRepository.findAll();

        List<soldReportModel> soldReports = new ArrayList<>();

        for (BookEntity book : books) {
            soldReportModel report = new soldReportModel();
            report.setName(book.getName());
            report.setSoldQuantity(book.getSold());
            soldReports.add(report);
        }
        return soldReports;
    }


    @Override
    public Optional<BookEntity> findByIdOrName(int id, String name) {
        if (id != 0) {
            return bookRepository.findById(id);
        } else if (name != null && !name.isEmpty()) {
            return bookRepository.findByName(name);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<BookEntity> findByDatesBetween(LocalDate start, LocalDate end) {
        return bookRepository.findByPeriod(start, end);
    }

    @Override
    public List<BookEntity> findBestSell() {
        return bookRepository.findBestSeller();
    }
}
