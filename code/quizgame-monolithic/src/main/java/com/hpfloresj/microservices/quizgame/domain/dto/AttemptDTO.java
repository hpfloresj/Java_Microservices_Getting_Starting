package com.hpfloresj.microservices.quizgame.domain.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Date;

@RequiredArgsConstructor
@ToString
@Data
public final class AttemptDTO {

    private final Long id;

    private final Date attemptDate;

    private final int quizSize;

    private final int score;

    private final String user;

    private final int valid;

    private final int failed;
}
