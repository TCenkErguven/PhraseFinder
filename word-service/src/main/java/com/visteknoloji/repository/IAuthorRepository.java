package com.visteknoloji.repository;

import com.visteknoloji.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAuthorRepository extends JpaRepository<Author,Integer> {
    Optional<Author> findByAuthorNameAndAuthorSurnameIgnoreCase(String authorName,String authorSurname);
}
