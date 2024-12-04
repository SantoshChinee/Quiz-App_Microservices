package com.Santosh.question_service.Service;


import com.Santosh.question_service.Model.AnswerSheet;
import com.Santosh.question_service.Model.Question;
import com.Santosh.question_service.Model.QuestionWrapper;
import com.Santosh.question_service.Repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuetionService {
    @Autowired
    QuestionRepo questionRepo;

    public ResponseEntity<List<Question>> getAllQuestions() {
        return new ResponseEntity<>( questionRepo.findAll(), HttpStatus.OK);
    }

    public List<Question> getQuestionsBasedOnCategory(String category) {
       return questionRepo.findByDifficultyLevel(category);
    }

    public Question addQuestion(Question question) {
        return questionRepo.save(question);
    }

    public void deleteQuestion(Integer Id) {
        questionRepo.deleteById(Id);
    }

    public List<Integer> getQuizQuestions(String category, Integer numQ) {
        return questionRepo.CreateQuizRandomlyBasedOnCategory(category,numQ);
    }

    public List<QuestionWrapper> getQuestionsBasedOnId(List<Integer> integers) {
        List<QuestionWrapper> questionWrapper =  new ArrayList<>();
        List<Question>questions = new ArrayList<>();
        for(Integer id : integers){
            questions.add(questionRepo.findById(id).get());
        }
        for(Question question : questions){
            QuestionWrapper questionWrapper1 = new QuestionWrapper();
            questionWrapper1.setId(question.getId());
            questionWrapper1.setQuestionTitle(question.getQuestionTitle());
            questionWrapper1.setOption1(question.getOption1());
            questionWrapper1.setOption2(question.getOption2());
            questionWrapper1.setOption3(question.getOption3());
            questionWrapper1.setOption4(question.getOption4());
            questionWrapper.add(questionWrapper1);
        }
        return questionWrapper;
    }

    public Integer calculateScore(List<AnswerSheet> answerSheets) {
        int score = 0;
        for(AnswerSheet answerSheet : answerSheets){
            Question question = questionRepo.findById(answerSheet.getId()).get();
            if(answerSheet.getAnswer().equals(question.getRightAnswer())){
                score++;
            }
        }
        return score;
    }
}
