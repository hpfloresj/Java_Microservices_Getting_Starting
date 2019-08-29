package com.hpfloresj.microservices.gamification.domain.entity;

import com.hpfloresj.microservices.gamification.domain.enums.BadgeType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@RequiredArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "BADGE_CARD")
public final class BadgeCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BADGE_CARD_ID")
    private final Long id;

    @Column(name = "USER_ID")
    private final Long userId;

    @Column(name = "BADGE_TS")
    private final long badgeTimestamp;

    private final BadgeType badgeType;

    /**
     * JPA/JSON Constructor
     */
    public BadgeCard() {
        this(null, null, 0, null);
    }

    public BadgeCard(Long userId, BadgeType badgeType) {
        this(null, userId, System.currentTimeMillis(), badgeType);
    }
}
