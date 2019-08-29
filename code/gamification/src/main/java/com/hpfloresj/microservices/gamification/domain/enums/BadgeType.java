package com.hpfloresj.microservices.gamification.domain.enums;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum BadgeType {
    BRONZE(100),
    SILVER(200),
    GOLD(300),
    FIRST_WON(0);

    private static ImmutableMap<Integer, BadgeType> reverseLookupCode = Maps.uniqueIndex(Arrays.asList(BadgeType.values()), BadgeType::getScoreThreshold);
    private final int scoreThreshold;

    public static BadgeType fromScoreThreshold(int scoreThreshold) {
        BadgeType badgeType = reverseLookupCode.get(scoreThreshold);
        if (badgeType != null) {
            return badgeType;
        }
        throw new IllegalArgumentException("Invalid BadgeType");
    }

}
