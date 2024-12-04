package com.Santosh.quiz_service.faign;

import com.Santosh.quiz_service.Model.AnswerSheet;
import com.Santosh.quiz_service.Model.QuestionWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
    @GetMapping("/Questions/generate")
    public List<Integer> getAllQuestionsForQuiz(@RequestParam String category, @RequestParam Integer numQ);

    @PostMapping("/Questions/generatedQ")
    public List<QuestionWrapper> getQuestionsBasedOnId(@RequestBody List<Integer> integers);

    @PostMapping("/Questions/score")
    public Integer calculateScore(@RequestBody List<AnswerSheet> answerSheets);
}
