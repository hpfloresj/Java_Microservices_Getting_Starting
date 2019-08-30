package com.hpfloresj.microservices.gamification.service;

import com.hpfloresj.microservices.gamification.domain.entity.ScoreCard;
import com.hpfloresj.microservices.gamification.repository.ScoreCardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final ScoreCardRepository scoreCardRepository;

    @Override
    public void newAttemptByUser(Long userId, Long attemptId, boolean passTest) {
        if (passTest) {
            ScoreCard scoreCard = new ScoreCard(userId, attemptId);
            scoreCardRepository.save(scoreCard);
        }
    }

}
