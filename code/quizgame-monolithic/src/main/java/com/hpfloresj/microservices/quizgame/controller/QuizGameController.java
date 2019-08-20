package com.hpfloresj.microservices.quizgame.controller;

import com.hpfloresj.microservices.quizgame.domain.Question;
import com.hpfloresj.microservices.quizgame.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuizGameController {

    private final QuizService quizService;

    /**
     * Get questions randomly-generated
     * Equivalent @RequestMapping(method = RequestMethod.GET)
     *
     * @return
     */
    @GetMapping(path = "/random", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Question>> getRandom() {
        return ResponseEntity.ok(quizService.createRandomQuestions());
    }

}
