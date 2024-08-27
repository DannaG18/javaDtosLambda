package com.fixing;

import java.text.MessageFormat;
import java.util.Optional;

import com.fixing.application.FindSurveyDtoByIdUC;
import com.fixing.domain.dto.SurveyDto;
import com.fixing.domain.service.SurveyService;
import com.fixing.infrastructure.repository.SurveyRepository;


public class Main {
    public static void main(String[] args) {
        SurveyService surveyService = new SurveyRepository();
        FindSurveyDtoByIdUC findSurveyDtoByIdUC = new FindSurveyDtoByIdUC(surveyService);
        Optional<SurveyDto> surveyDto = findSurveyDtoByIdUC.execute(2);
        surveyDto.ifPresentOrElse(survey -> {
            survey.getChildChapter().forEach(data -> {

                System.out.println(MessageFormat.format("Chapter {0} : {1} ", data.getChapterNumber(), data.getName() ));
                data.getChildQuestion().forEach(question -> {
                    System.out.println(question.getName());
                });
            });
        },
        () -> System.out.println("Survey not found")); 
    }
}