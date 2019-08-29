package com.hpfloresj.microservices.gamification.service;

import com.hpfloresj.microservices.gamification.domain.dto.GameStatsDTO;

public interface GameService {

    GameStatsDTO newAttemptByUser(Long userId, Long attemptId, boolean passTest);

    GameStatsDTO getStartByUser(Long userId);
}
