package com.fixing.domain.service;

import java.util.List;
import java.util.Optional;

import com.fixing.domain.dto.ChapterDto;
import com.fixing.domain.entity.Chapter;

public interface ChapterService {
    void createChapter (Chapter chapter);
    Optional<Chapter> findChapter (int id);
    void updateChapter (Chapter chapter);
    void deleteChapter (int id);
    List<Chapter> findChapterBySurvey(int surveyId);
    List<ChapterDto> findChapterDto (int id);
}
