package com.example.bookmanager.controller;

import com.example.bookmanager.entity.BookEntity;
import com.example.bookmanager.exception.BookNotFoundException;
import com.example.bookmanager.model.soldReportModel;
import com.example.bookmanager.repository.BookRepository;
import com.example.bookmanager.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/auth/user/book")
@RequestMapping("/api/book")
@CrossOrigin
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Iterator<BookEntity> getAllBook() {
        return  bookService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public BookEntity getById(@PathVariable int id) {
        return bookService.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Không tìm thấy sách với ID: " + id));
    }

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public BookEntity createBook(@RequestBody BookEntity book ) {
        return bookService.create(book);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public BookEntity updateBook(@RequestBody BookEntity book ) {
        BookEntity bookEntity = bookService.findById(book.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        bookEntity.setId(book.getId());
        bookEntity.setName(book.getName());
        bookEntity.setPrice(book.getPrice());
        bookEntity.setQuantity(book.getQuantity());
        return bookService.update(book);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String deleteBook(@PathVariable int id) {
        if (bookService.delete(bookService.findById(id).get())) {
            return "Deleted successfully";
        }
        return "Delete failed";
    }

    @PutMapping("/sell")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String soldBook(@RequestParam int id, @RequestParam int quantity) {
        return bookService.sellBook(id, quantity);
    }

    @GetMapping("/sortByPrice")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<BookEntity> sortByPrice() {
        return bookService.sortByPrice();
    }

    @GetMapping("/sortBySold")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<BookEntity> sortBySold() {
        return bookService.sortBySold();
    }

    @GetMapping("/soldReport")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<soldReportModel> soldReport() {
        return bookService.soldReport();
    }

//    @GetMapping("/search")
//    public BookEntity searchBookbyIdOrName(@RequestParam int id, @RequestParam String name) {
//        Optional<BookEntity> foundBook = bookService.findByIdOrName(id, name);
//        return foundBook.orElseThrow(() -> new BookNotFoundException("Không tìm thấy sách với ID: " + id));
//    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public BookEntity searchBookByIdOrName(
            @RequestParam(value = "id", required = false, defaultValue = "0") int id,
            @RequestParam(value = "name", required = false) String name) {
        Optional<BookEntity> foundBook = bookService.findByIdOrName(id != 0 ? id : 0, name);
        return foundBook.orElseThrow(() -> new BookNotFoundException("Không tìm thấy sách với ID: " + id));
    }

    @GetMapping("/searchdate")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<BookEntity> searchBooksByEntryDate(@RequestParam LocalDate start, @RequestParam LocalDate end) {
        if (start == null && end == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please provide either start or end date.");
        }
        List<BookEntity> books = bookService.findByDatesBetween(start, end);
        return books;
    }

    @GetMapping("/bestseller")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<soldReportModel> findBestSeller() {
        List<BookEntity> books = bookService.findBestSell();

        List<soldReportModel> soldReports = new ArrayList<>();

        for (BookEntity book : books) {
            soldReportModel report = new soldReportModel();
            report.setName(book.getName());
            report.setSoldQuantity(book.getSold());
            soldReports.add(report);
        }
        return soldReports;
    }
}
