package com.hpfloresj.microservices.gamification.controller;

import com.hpfloresj.microservices.gamification.domain.dto.GameStatsDTO;
import com.hpfloresj.microservices.gamification.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
@RequiredArgsConstructor
public class UserStatsController {

    private final GameService gameService;

    @GetMapping
    public GameStatsDTO getStatsForUser(@RequestParam("userId") final Long userId) {
        return gameService.getStartByUser(userId);
    }
}
