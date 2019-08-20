package com.hpfloresj.microservices.quizgame.repository;

import com.hpfloresj.microservices.quizgame.domain.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttemptRepository extends JpaRepository<Attempt, Long> {
    /**
     * @param userAlias
     * @return the latest 5 attempts by userAlias
     */
    List<Attempt> findTop5ByUserAliasOrderByIdDesc(String userAlias);
}
