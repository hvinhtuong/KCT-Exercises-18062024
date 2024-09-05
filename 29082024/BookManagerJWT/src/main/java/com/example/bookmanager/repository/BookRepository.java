package com.example.bookmanager.repository;

import com.example.bookmanager.entity.BookEntity;
import com.example.bookmanager.model.soldReportModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    @Query("SELECT b FROM BookEntity b WHERE b.name = :name")
    Optional<BookEntity> findByName(@Param("name") String name);

    @Query("SELECT b FROM BookEntity b WHERE b.entryDate BETWEEN :start AND :end")
    List<BookEntity> findByPeriod(@Param("start") LocalDate start, @Param("end") LocalDate end);

    @Query("SELECT b FROM BookEntity b ORDER BY b.sold DESC FETCH FIRST 5 ROWS ONLY")
    List<BookEntity> findBestSeller();
}
