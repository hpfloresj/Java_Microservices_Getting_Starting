package com.hpfloresj.microservices.gamification.repository;

import com.hpfloresj.microservices.gamification.domain.entity.BadgeCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BadgeCardRepository extends JpaRepository<BadgeCard, Long> {

    List<BadgeCard> findByUserIdOrderByBadgeTimestampDesc(final Long userId);
}
