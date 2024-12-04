package com.Santosh.quiz_service.Service;


import com.Santosh.quiz_service.Model.AnswerSheet;
import com.Santosh.quiz_service.Model.QuestionWrapper;
import com.Santosh.quiz_service.Model.Quiz;
import com.Santosh.quiz_service.Repo.QuizRepo;
import com.Santosh.quiz_service.faign.QuizInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {
    @Autowired
    QuizRepo quizRepo;
    @Autowired
    QuizInterface quizInterface;

    public String CreateQuiz(String title, Integer numQ, String category) {
        List<Integer> questions = quizInterface.getAllQuestionsForQuiz(category,numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepo.save(quiz);
        return "Success";
    }

    public List<QuestionWrapper> getQuizBasedOnId(Integer id) {
        Quiz quiz = quizRepo.findById(id).get();
        List<Integer> questionIds = quiz.getQuestions();
        return quizInterface.getQuestionsBasedOnId(questionIds);
    }

    public Integer submitQuiz(Integer id, List<AnswerSheet> answerSheets) {
        Integer i = quizInterface.calculateScore(answerSheets);
        return i;
    }
}
