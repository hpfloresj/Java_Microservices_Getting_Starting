package com.hpfloresj.microservices.gamification.controller;

import com.hpfloresj.microservices.gamification.domain.dto.LeaderBoardDTO;
import com.hpfloresj.microservices.gamification.service.LeaderBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/leaders")
@RequiredArgsConstructor
public class LeaderBoardController {
    private final LeaderBoardService leaderBoardService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LeaderBoardDTO> getLeaderBoard() {
        return leaderBoardService.getCurrentLeaderBoard();
    }
}
