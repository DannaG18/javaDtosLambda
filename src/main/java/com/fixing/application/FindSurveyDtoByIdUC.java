package com.fixing.application;

import java.util.Optional;

import com.fixing.domain.dto.SurveyDto;
import com.fixing.domain.entity.Survey;
import com.fixing.domain.service.SurveyService;

public class FindSurveyDtoByIdUC {
    private final SurveyService surveyService;

    public FindSurveyDtoByIdUC(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    public Optional<SurveyDto> execute(int id) {
        return surveyService.findSurveyDto(id);
    }
}
