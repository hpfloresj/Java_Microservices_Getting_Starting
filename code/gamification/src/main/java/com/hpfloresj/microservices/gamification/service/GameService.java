package com.hpfloresj.microservices.gamification.service;

public interface GameService {

    void newAttemptByUser(Long userId, Long attemptId, boolean passTest);
}
