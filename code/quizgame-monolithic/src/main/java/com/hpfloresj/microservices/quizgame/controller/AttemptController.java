package com.hpfloresj.microservices.quizgame.controller;

import com.hpfloresj.microservices.quizgame.domain.Attempt;
import com.hpfloresj.microservices.quizgame.dto.AttemptDTO;
import com.hpfloresj.microservices.quizgame.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attempt")
@RequiredArgsConstructor
public class AttemptController {

    private final QuizService quizService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AttemptDTO> postResult(@RequestBody Attempt attempt) {
        return ResponseEntity.ok(quizService.checkAttempt(attempt));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AttemptDTO>> getStatistics(@RequestParam("alias") String alias) {
        return ResponseEntity.ok(quizService.getStatByUser(alias));
    }

}
