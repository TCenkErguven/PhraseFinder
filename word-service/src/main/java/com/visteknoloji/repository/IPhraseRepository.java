package com.visteknoloji.repository;

import com.visteknoloji.entity.Phrase;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IPhraseRepository extends JpaRepository<Phrase,Integer> {
    Boolean existsByAuthorPhraseIgnoreCase(String authorPhrase);
}
