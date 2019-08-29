package com.hpfloresj.microservices.gamification.domain.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public final class LeaderBoardDTO {
    private final Long userId;
    private final Long totalScore;

    public LeaderBoardDTO() {
        this(0L, 0L);
    }
}
