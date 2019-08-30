package com.hpfloresj.microservices.quizgame.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "answer")
public final class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long id;

    @Column(name = "display_text", length = 600, nullable = false)
    private String displayText;

    @Column(name = "correct_answer", nullable = false)
    @JsonIgnore
    private boolean correctAnswer;

    @ManyToOne
    @JoinColumn(name = "question_id")
    @JsonIgnore
    private Question question;

    public Answer(String displayText, boolean correctAnswer, Question question) {
        this.displayText = displayText;
        this.correctAnswer = correctAnswer;
        this.question = question;
    }
}
