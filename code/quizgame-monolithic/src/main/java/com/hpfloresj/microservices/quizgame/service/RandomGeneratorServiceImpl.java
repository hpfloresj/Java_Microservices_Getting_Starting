package com.hpfloresj.microservices.quizgame.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class RandomGeneratorServiceImpl implements RandomGeneratorService {

    /**
     * @see RandomGeneratorService#generateRandom(List, int)
     */
    @Override
    public List<Long> generateRandom(final List<Long> questions, int quizLength){
        Random random = new Random();
        List<Long> numbers = new ArrayList<>();
        do {
            int next = random.nextInt(questions.size());
            if (!numbers.contains(questions.get(next))) {
                numbers.add(questions.get(next));
            }
        } while (numbers.size() < quizLength);
        return numbers;
    }
}
