package com.alexic0n.anagramr.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static com.alexic0n.anagramr.util.AnagramUtil.orderString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Anagram {

    @Id
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
        this.orderedInput = orderString(input);
        this.createdDateTime = LocalDateTime.now();
    }

}
