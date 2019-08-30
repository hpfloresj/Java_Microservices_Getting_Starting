package com.hpfloresj.microservices.gamification.service;

import com.hpfloresj.microservices.gamification.domain.dto.LeaderBoardDTO;

import java.util.List;

public interface LeaderBoardService {

    List<LeaderBoardDTO> getCurrentLeaderBoard();
}
