package com.hpfloresj.microservices.gamification.service;

import com.hpfloresj.microservices.gamification.domain.dto.LeaderBoardDTO;
import com.hpfloresj.microservices.gamification.repository.ScoreCardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LeaderBoardServiceImpl implements LeaderBoardService {

    private final ScoreCardRepository scoreCardRepository;

    @Override
    public List<LeaderBoardDTO> getCurrentLeaderBoard() {
        return scoreCardRepository.findFirst10();
    }
}
