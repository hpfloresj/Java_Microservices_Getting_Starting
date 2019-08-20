package com.hpfloresj.microservices.quizgame.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Class that represents the attempt of user by resolve the quiz game
 */
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "attempt")
public final class Attempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attempt_id")
    private Long id;

    @JsonIgnore
    @Column(nullable = false)
    private Date attemptDate;

    @JsonIgnore
    @Column(nullable = false)
    private int score;

    @Column( name = "quiz_size", nullable = false)
    private int quizSize;

    @JsonIgnore
    @Column(nullable = false)
    private int valid;

    @JsonIgnore
    @Column(nullable = false)
    private int failed;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "attempt", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<QuestionResponse> responses;

    public Attempt(User user, int score, int quizSize, int valid, List<QuestionResponse> responses) {
        this.user = user;
        this.attemptDate = new Date();
        this.score = score;
        this.quizSize = quizSize;
        this.valid = valid;
        this.failed = quizSize - valid;
        this.responses = responses;
    }
}
