package com.example.project.repository;

import com.example.project.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByIsBorrowedFalseAndIsRemovedFalse();

    @Transactional
    @Modifying
    @Query("update Book b set b.isRemoved = ?1 where b.id = ?2")
    void updateIsRemovedById(Boolean isRemoved, Integer id);

    boolean existsByIsBorrowedFalseAndIsRemovedFalseAndId(Integer id);


    @Transactional
    @Modifying
    @Query("update Book b set b.isBorrowed = ?1 where b.id = ?2")
    void updateIsBorrowedById(Boolean isBorrowed, Integer id);




}
