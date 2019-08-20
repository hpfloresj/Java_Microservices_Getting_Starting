package com.hpfloresj.microservices.quizgame.service;


import com.hpfloresj.microservices.quizgame.domain.Attempt;
import com.hpfloresj.microservices.quizgame.domain.Question;
import com.hpfloresj.microservices.quizgame.dto.AttemptDTO;

import java.util.List;

public interface QuizService {

    /**
     *
     * @return
     */
    List<Question> createRandomQuestions();

    /**
     * @param resultAttempt
     * @return Check a attempt, return a score between 0 and 100 percent
     */
    AttemptDTO checkAttempt(Attempt resultAttempt);

    /**
     * Get stat by user
     * @param userAlias
     * @return
     */
    List<AttemptDTO> getStatByUser(String userAlias);
}
