package com.Santosh.question_service.Repo;


import com.Santosh.question_service.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question,Integer> {
    List<Question> findByDifficultyLevel(String difficultyLevel);
    @Query(value = "SELECT q.Id FROM questions q WHERE q.difficulty_level = :category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
    List<Integer> CreateQuizRandomlyBasedOnCategory(@Param("category") String category, @Param("numQ") Integer numQ);

}
