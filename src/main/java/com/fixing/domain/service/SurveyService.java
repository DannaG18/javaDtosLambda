package com.fixing.domain.service;
import java.util.List;
import java.util.Optional;

import com.fixing.domain.dto.SurveyDto;
import com.fixing.domain.entity.Survey;

public interface SurveyService {
    void createSurvey (Survey survey);
    Optional<Survey> findSurvey (int id);
    Optional<Survey> findSurveyByName(String name);
    void updateSurvey (Survey survey);
    void deleteSurvey (int id);
    void getAllSurvey(List<Survey> names);
    Optional<SurveyDto> findSurveyDto (int id);
}
