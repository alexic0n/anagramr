package com.alexic0n.anagramr.service;

import com.alexic0n.anagramr.repository.AnagramRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AnagramServiceTest {

    @Autowired
    AnagramRepository repository;

    @Autowired
    AnagramService service;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void isAnagram() {
        String originalInput = "test";
        String anagramOfOriginal = "tset";
        String notAnagramOfOriginal = "blah";
        assertFalse(service.isAnagram(originalInput, notAnagramOfOriginal));
        assertEquals(2, repository.findAll().size());
        assertTrue(service.isAnagram(originalInput, anagramOfOriginal));
        assertEquals(3, repository.findAll().size());
    }

    @Test
    void getAllAnagramsOfInput() {
        String originalInput = "test";
        String anagramOfOriginal = "tset";
        String notAnagramOfOriginal = "blah";
        service.isAnagram(originalInput, notAnagramOfOriginal);
        service.isAnagram(originalInput, anagramOfOriginal);
        assertEquals(0, service.getAllAnagramsOfInput(notAnagramOfOriginal).size());
        assertEquals(2, service.getAllAnagramsOfInput("estt").size());
    }

    @Test
    void getAllAnagrams() {
        String originalInput = "test";
        String anagramOfOriginal = "tset";
        String notAnagramOfOriginal = "blah";
        service.isAnagram(originalInput, notAnagramOfOriginal);
        service.isAnagram(originalInput, anagramOfOriginal);
        assertEquals(3, service.getAllAnagrams().size());
    }

    @Test
    void deleteAnagramByInput() {
        String originalInput = "test";
        String anagramOfOriginal = "tset";
        String notAnagramOfOriginal = "blah";
        service.isAnagram(originalInput, notAnagramOfOriginal);
        service.isAnagram(originalInput, anagramOfOriginal);
        service.deleteAnagramByInput(anagramOfOriginal);
        assertEquals(2, service.getAllAnagrams().size());
    }

    @Test
    void deleteAllAnagrams() {
        String originalInput = "test";
        String anagramOfOriginal = "tset";
        String notAnagramOfOriginal = "blah";
        service.isAnagram(originalInput, notAnagramOfOriginal);
        service.isAnagram(originalInput, anagramOfOriginal);
        assertEquals(3, service.getAllAnagrams().size());
        service.deleteAllAnagrams();
        assertEquals(0, service.getAllAnagrams().size());
    }
}