package com.alexic0n.anagramr.service;

import com.alexic0n.anagramr.model.Anagram;
import com.alexic0n.anagramr.repository.AnagramRepository;
import org.springframework.stereotype.Service;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;

import java.util.List;

import static com.alexic0n.anagramr.util.AnagramUtil.orderString;

@BrowserCallable
@AnonymousAllowed
@Service
public class AnagramService {

    private final AnagramRepository repository;

    public AnagramService(AnagramRepository repository) {
        this.repository = repository;
    }

    public boolean isAnagram(String input1, String input2) {
        Anagram anagram1 = saveAnagram(input1);
        Anagram anagram2 = saveAnagram(input2);
        return anagram1.getOrderedInput().equals(anagram2.getOrderedInput());
    }

    public List<Anagram> getAllAnagramsOfInput(String input) {
        return repository.findAnagramByOrderedInputAndInputNot(orderString(input), input);
    }

    private Anagram saveAnagram(String input) {
        return repository.findById(input).orElseGet(() -> repository.save(new Anagram(input)));
    }
    
}