package com.hpfloresj.microservices.gamification.repository;

import com.hpfloresj.microservices.gamification.domain.dto.LeaderBoardDTO;
import com.hpfloresj.microservices.gamification.domain.entity.ScoreCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScoreCardRepository extends JpaRepository<ScoreCard, Long> {

    @Query("select new com.hpfloresj.microservices.gamification.domain.dto.LeaderBoardDTO(s.userId, sum(s.score)) " +
            "from com.hpfloresj.microservices.gamification.domain.entity.ScoreCard s " +
            "group by s.userId order by sum(s.score) desc")
    List<LeaderBoardDTO> findFirst10();

}
