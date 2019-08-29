package com.hpfloresj.microservices.gamification.service;

import com.hpfloresj.microservices.gamification.domain.dto.GameStatsDTO;
import com.hpfloresj.microservices.gamification.domain.entity.BadgeCard;
import com.hpfloresj.microservices.gamification.domain.entity.ScoreCard;
import com.hpfloresj.microservices.gamification.domain.enums.BadgeType;
import com.hpfloresj.microservices.gamification.repository.BadgeCardRepository;
import com.hpfloresj.microservices.gamification.repository.ScoreCardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final ScoreCardRepository scoreCardRepository;
    private final BadgeCardRepository badgeCardRepository;

    @Override
    public GameStatsDTO newAttemptByUser(Long userId, Long attemptId, boolean passTest) {
        if (passTest) {
            ScoreCard scoreCard = new ScoreCard(userId, attemptId);
            scoreCardRepository.save(scoreCard);
            List<BadgeCard> badgeCards = processForBadges(userId, attemptId);
            return new GameStatsDTO(userId,
                    scoreCard.getScore(),
                    badgeCards.stream().map(BadgeCard::getBadgeType).collect(Collectors.toList()));
        }
        return GameStatsDTO.emptyStats(userId);
    }

    @Override
    public GameStatsDTO getStartByUser(Long userId) {
        int score = scoreCardRepository.getTotalScoreByUser(userId);
        List<BadgeCard> badgeCards = badgeCardRepository.findByUserIdOrderByBadgeTimestampDesc(userId);
        return new GameStatsDTO(userId, score, badgeCards.stream()
                .map(BadgeCard::getBadgeType)
                .collect(Collectors.toList()));
    }

    /**
     * Checks the total score and the different score cards obtained
     * to give new badges in case their conditions are met.
     */
    private List<BadgeCard> processForBadges(final Long userId, final Long attemptId) {
        List<BadgeCard> badgeCards = new ArrayList<>();
        int totalScore = scoreCardRepository.getTotalScoreByUser(userId);
        log.info("New score for user {} is {}", userId, totalScore);
        List<ScoreCard> scoreCardList = scoreCardRepository.findByUserIdOrderByScoreTimestampDesc(userId);
        List<BadgeCard> badgeCardList = badgeCardRepository.findByUserIdOrderByBadgeTimestampDesc(userId);

        // Badges depending on score
        checkAndGiveBadges(badgeCardList, BadgeType.BRONZE, totalScore,BadgeType.BRONZE.getScoreThreshold(), userId).ifPresent(badgeCards::add);
        checkAndGiveBadges(badgeCardList, BadgeType.SILVER, totalScore,BadgeType.SILVER.getScoreThreshold(), userId).ifPresent(badgeCards::add);
        checkAndGiveBadges(badgeCardList, BadgeType.GOLD, totalScore,BadgeType.GOLD.getScoreThreshold(), userId).ifPresent(badgeCards::add);

        if (scoreCardList.size() == 1
                && !containsBadge(badgeCardList, BadgeType.FIRST_WON)) {
            BadgeCard firstWonBadge = giveBadgeToUser(BadgeType.FIRST_WON, userId);
            badgeCards.add(firstWonBadge);
        }
        return badgeCards;
    }

    private Optional<BadgeCard> checkAndGiveBadges(final List<BadgeCard> badgeCards,
                                                   final BadgeType badgeType,
                                                   final int score,
                                                   final int scoreThreshold,
                                                   final Long userId) {
        if (score >= scoreThreshold
                && !containsBadge(badgeCards, badgeType)) {
            return Optional.of(giveBadgeToUser(badgeType, userId));
        }
        return Optional.empty();
    }

    /**
     * Checks if the passed list of badges includes the one  being checked
     */
    private boolean containsBadge(final List<BadgeCard> badgeCards, final BadgeType badgeType) {
        return badgeCards.stream().anyMatch(b -> b.getBadgeType().equals(badgeType));
    }

    /**
     * Assigns a new badge to the given user
     */
    private BadgeCard giveBadgeToUser(final BadgeType badgeType, final Long userId) {
        BadgeCard badgeCard = new BadgeCard(userId, badgeType);
        return badgeCardRepository.save(badgeCard);
    }

}
