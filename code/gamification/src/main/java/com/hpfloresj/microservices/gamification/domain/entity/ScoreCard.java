package com.hpfloresj.microservices.gamification.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * This class represents the score linked to an attempt in the game, with an
 * associated user and the timestamp in which the score is registered.
 */
@RequiredArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "SCORE_CARD")
public final class ScoreCard {

    public static final int DEFAULT_SCORE = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SCORE_CARD_ID")
    private final Long id;

    @Column(name = "USER_ID")
    private final Long userId;

    @Column(name = "ATTEMPT_ID")
    private final Long attemptId;

    @Column(name = "SCORE_TS")
    private final long scoreTimestamp;

    @Column(name = "SCORE")
    private final int score;

    /**
     * JPA/JSON Constructor
     */
    public ScoreCard() {
        this(null, null, null, 0, 0);
    }

    public ScoreCard(final Long userId, final Long attemptId) {
        this(null, userId, attemptId, System.currentTimeMillis(), DEFAULT_SCORE);
    }

}
