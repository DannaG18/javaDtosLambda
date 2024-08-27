package com.fixing.domain.service;
import java.util.List;
import java.util.Optional;

import com.fixing.domain.dto.QuestionDto;
import com.fixing.domain.entity.Question;

public interface QuestionService {
    Optional<Question> findQuestion (int id);
    List<QuestionDto> findQuestionDto (int id);
}
