package com.hpfloresj.microservices.gamification.domain.dto;

import com.hpfloresj.microservices.gamification.domain.enums.BadgeType;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Data
public final class GameStatsDTO {
    private final Long userId;
    private final int score;
    private final List<BadgeType> badgeTypes;

    /**
     * JPA/JSON Constructor
     */
    public GameStatsDTO() {
        this.userId = 0L;
        this.score = 0;
        this.badgeTypes = new ArrayList<>();
    }

    public static GameStatsDTO emptyStats(final Long userId) {
        return new GameStatsDTO(userId, 0, Collections.emptyList());
    }
}
