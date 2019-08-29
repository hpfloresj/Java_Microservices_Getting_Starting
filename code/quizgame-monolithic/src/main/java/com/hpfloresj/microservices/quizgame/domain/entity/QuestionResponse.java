package com.hpfloresj.microservices.quizgame.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "question_response")
public final class QuestionResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_response_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;

    @ManyToOne
    @JoinColumn(name = "attempt_id", nullable = false)
    private Attempt attempt;

    public QuestionResponse(Question question, Answer answer, Attempt attempt) {
        this.question = question;
        this.answer = answer;
        this.attempt = attempt;
    }

}
