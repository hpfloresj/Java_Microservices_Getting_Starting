package com.hpfloresj.microservices.quizgame.service;

import com.hpfloresj.microservices.quizgame.domain.entity.Attempt;
import com.hpfloresj.microservices.quizgame.domain.entity.Question;
import com.hpfloresj.microservices.quizgame.domain.entity.QuestionResponse;
import com.hpfloresj.microservices.quizgame.domain.entity.User;
import com.hpfloresj.microservices.quizgame.domain.dto.AttemptDTO;
import com.hpfloresj.microservices.quizgame.repository.AttemptRepository;
import com.hpfloresj.microservices.quizgame.repository.QuestionRepository;
import com.hpfloresj.microservices.quizgame.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final RandomGeneratorService randomGeneratorService;

    private final AttemptRepository attemptRepository;

    private final QuestionRepository questionRepository;

    private final UserRepository userRepository;

    @Value("${quiz.length}")
    private int quizSize;

    /**
     * @see QuizService#createRandomQuestions()
     */
    public List<Question> createRandomQuestions() {
        List<Long> identifiers = randomGeneratorService.generateRandom(
                questionRepository.findAll()
                        .stream()
                        .map(Question::getId)
                        .collect(Collectors.toList()), quizSize);
        return questionRepository.findByQuestionIDs(identifiers);
    }

    /**
     * @see QuizService#checkAttempt(Attempt)
     */
    @Transactional
    @Override
    public AttemptDTO checkAttempt(final Attempt resultAttempt) {
        Optional<User> user = userRepository.findByAlias(resultAttempt.getUser().getAlias());

        // Verify score
        int valid = 0;
        List<Question> questionsDB = questionRepository.findAll();

        for (QuestionResponse questionResponse : resultAttempt.getResponses()) {
            Optional<Question> question = questionsDB.stream().filter(questionDB -> questionDB.getId().equals(questionResponse.getQuestion().getId())).findFirst();
            if (question.isPresent()
                    && questionResponse.getAnswer() != null
                    && question.get().getCorrectAnswer().get().getId().equals(questionResponse.getAnswer().getId())) {
                valid++;
            }
        }

        int score = Math.round(valid * 100 / quizSize);

        // Save
        Attempt attemptCopy = new Attempt(
                user.orElse(resultAttempt.getUser()),
                score,
                quizSize,
                valid,
                null);

        List<QuestionResponse> questionResponses = new ArrayList<>();

        for (QuestionResponse questionResponse : resultAttempt.getResponses()) {
            QuestionResponse questionResponseCopy = new QuestionResponse(questionResponse.getQuestion(), questionResponse.getAnswer(), attemptCopy);
            questionResponses.add(questionResponseCopy);
        }

        attemptCopy.setResponses(questionResponses);
        attemptCopy = attemptRepository.save(attemptCopy);

        return new AttemptDTO(attemptCopy.getId(),
                attemptCopy.getAttemptDate(),
                attemptCopy.getQuizSize(),
                score,
                attemptCopy.getUser().getAlias(),
                attemptCopy.getValid(),
                attemptCopy.getFailed());
    }

    /**
     * @see QuizService#getStatByUser(String)
     */
    @Override
    public List<AttemptDTO> getStatByUser(String userAlias) {
        List<AttemptDTO> list = new ArrayList<>();
        return attemptRepository.findTop5ByUserAliasOrderByIdDesc(userAlias).stream().map(attempt -> {
            AttemptDTO attemptDTO = new AttemptDTO(attempt.getId(),
                    attempt.getAttemptDate(),
                    attempt.getQuizSize(),
                    attempt.getScore(),
                    attempt.getUser().getAlias(),
                    attempt.getValid(),
                    attempt.getFailed());
            return attemptDTO;
        }).collect(Collectors.toList());
    }
}
