package com.Santosh.question_service.Model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AnswerSheet {
    private Integer id;
    private String answer;
}