package com.alexic0n.anagramr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alexic0n.anagramr.model.Anagram;
import java.util.List;


@Repository
public interface AnagramRepository extends JpaRepository<Anagram, String> {
    List<Anagram> findByOrderedInput(String orderedInput);

    List<Anagram> findAnagramByOrderedInputAndInputNot(String orderedInput, String input);
}
