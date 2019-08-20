package com.hpfloresj.microservices.quizgame.repository;

import com.hpfloresj.microservices.quizgame.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    // Spring JPA In cause using @Query
    @Query("select q from Question q where q.id in (:ids)")
    List<Question> findByQuestionIDs(@Param("ids") List<Long> ids);
}
