package com.Santosh.quiz_service.Controller;


import com.Santosh.quiz_service.Model.AnswerSheet;
import com.Santosh.quiz_service.Model.QuestionWrapper;
import com.Santosh.quiz_service.Model.QuizDTO;
import com.Santosh.quiz_service.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Quiz")
public class QuizController {
    @Autowired
    QuizService quizService;
    @PostMapping("/Create")
    public String crateQuiz(@RequestBody QuizDTO quizDTO)
    {
        return quizService.CreateQuiz(quizDTO.getTitle(),quizDTO.getNumQ(),quizDTO.getCategory());
    }
    @GetMapping("/get/{id}")
    public List<QuestionWrapper> getQuiz(@PathVariable Integer id){
        return quizService.getQuizBasedOnId(id);
    }
    @PostMapping("/Submit/{id}")
    public int submitQuiz(@PathVariable Integer id, @RequestBody List<AnswerSheet> answerSheets){
        System.out.println(answerSheets);
        return quizService.submitQuiz(id,answerSheets);
    }
}
