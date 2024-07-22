package com.alexic0n.anagramr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alexic0n.anagramr.model.Anagram;
import java.util.List;
import java.util.Optional;


@Repository
public interface AnagramRepository extends JpaRepository<Anagram, Long> {

    Optional<Anagram> findByInput(String input);

    List<Anagram> findByOrderedInputAndInputNot(String orderedInput, String input);
}
