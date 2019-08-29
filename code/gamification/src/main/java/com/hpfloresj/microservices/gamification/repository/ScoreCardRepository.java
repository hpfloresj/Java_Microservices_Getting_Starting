package com.hpfloresj.microservices.gamification.repository;

import com.hpfloresj.microservices.gamification.domain.dto.LeaderBoardDTO;
import com.hpfloresj.microservices.gamification.domain.entity.ScoreCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScoreCardRepository extends JpaRepository<ScoreCard, Long> {

    @Query("select sum(s.core) from ScoreCard s where s.userId = :userId group by s.userId")
    int getTotalScoreByUser(@Param("userId") final Long userId);

    @Query("select new LeaderBoardDTO(s.userId, sum(s.score)) from ScoreCard s group by s.userId order by sum(s.score) desc")
    List<LeaderBoardDTO> findFirst10();

    List<ScoreCard> findByUserIdOrderByScoreTimestampDesc(final Long userId);

}
