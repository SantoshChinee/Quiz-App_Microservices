package com.Santosh.question_service.Controller;


import com.Santosh.question_service.Model.AnswerSheet;
import com.Santosh.question_service.Model.Question;
import com.Santosh.question_service.Model.QuestionWrapper;
import com.Santosh.question_service.Service.QuetionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Questions")
public class QuestionController {
    @Autowired
    QuetionService quetionService;
    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> allLocation(){
        return quetionService.getAllQuestions();
    }

    @GetMapping("/Category/{category}")
    public List<Question> getQuestionsOnCategory(@PathVariable String category)
    {
        return quetionService.getQuestionsBasedOnCategory(category);
    }
    @PostMapping("/addQuestion")
    public Question addQuestion(@RequestBody Question question){
        return quetionService.addQuestion(question);
    }
    @DeleteMapping("/deleteQuestion/{id}")
    public void deleteQuestion(@PathVariable Integer id)
    {
        quetionService.deleteQuestion(id);
    }

    @GetMapping("/generate")
    public List<Integer> getAllQuestionsForQuiz(@RequestParam String category, @RequestParam Integer numQ)
    {
        return quetionService.getQuizQuestions(category,numQ);
    }
    @PostMapping("/generatedQ")
    public List<QuestionWrapper> getQuestionsBasedOnId(@RequestBody List<Integer> integers)
    {
        return quetionService.getQuestionsBasedOnId(integers);
    }
    @PostMapping("/score")
    public Integer calculateScore(@RequestBody List<AnswerSheet> answerSheets){
        return quetionService.calculateScore(answerSheets);
    }

}
