package com.example.lab3_server.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "questions")
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;
    @JsonProperty("text")
    private String text;
    public Question() {

    }
    public Question(String text) {
        this.text = text;
    }
    public void setText(String text) {
        this.text = text;
    }

}
