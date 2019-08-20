package com.hpfloresj.microservices.quizgame.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "question")
public final class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    @Column(nullable = false)
    private String prompt;

    @Column(length = 600)
    private String description;

    @OneToMany(mappedBy = "question", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<Answer> answers;

    public Question(String prompt, String description, List<Answer> answers) {
        this.prompt = prompt;
        this.description = description;
        this.answers = answers;
    }

    public Question(String prompt, String description) {
        this.prompt = prompt;
        this.description = description;
    }

    @JsonIgnore
    public Optional<Answer> getCorrectAnswer() {
        return answers.stream().filter(answer -> answer.isCorrectAnswer()).findFirst();
    }
}
