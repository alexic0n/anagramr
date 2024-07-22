package com.alexic0n.anagramr.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static com.alexic0n.anagramr.util.AnagramUtil.orderAndSanitizeString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Anagram {

    @Id
    @GeneratedValue
    Long id;

    @Column(unique=true)
    private String input;

    private String orderedInput;

    private LocalDateTime createdDateTime;

    public Anagram(
            String input
    ) {
        this.input = input;
    }

    @PrePersist
    private void setRequiredFields() {
        this.orderedInput = orderAndSanitizeString(input);
        this.createdDateTime = LocalDateTime.now();
    }

}
