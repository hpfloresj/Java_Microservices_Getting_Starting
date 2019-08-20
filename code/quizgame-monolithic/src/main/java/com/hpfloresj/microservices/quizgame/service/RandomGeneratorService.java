package com.hpfloresj.microservices.quizgame.service;

import java.util.List;

public interface RandomGeneratorService {

    /**
     * @return a randomly-generated from list identifiers
     */
    List<Long> generateRandom(final List<Long> questions, int quizLength);
}
