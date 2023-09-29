package com.visteknoloji.repository;

import com.visteknoloji.entity.Phrase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IPhraseRepository extends JpaRepository<Phrase,Integer> {
    Boolean existsByAuthorPhraseIgnoreCase(String authorPhrase);

    @Query("SELECT COUNT(p) FROM Phrase p")
    Long countPhrasesBy();

}
