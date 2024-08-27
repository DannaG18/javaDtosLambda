package com.fixing.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Optional;

import com.fixing.domain.dto.ChapterDto;
import com.fixing.domain.dto.QuestionDto;
import com.fixing.domain.entity.Chapter;
import com.fixing.domain.service.ChapterService;
import com.fixing.infrastructure.repository.QuestionRepository;

public class ChapterRepository implements ChapterService{
    private Connection connection;
    private QuestionRepository questionRepository;

    public ChapterRepository() {
        this.questionRepository = new QuestionRepository();
        try {
            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream("configdb.properties"));
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createChapter(Chapter chapter) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createChapter'");
    }

    @Override
    public Optional<Chapter> findChapter(int id) {
        String sql = "SELECT * FROM chapter WHERE id = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    Chapter chapter = new Chapter(rs.getInt("id"), rs.getDate("created_at"), rs.getDate("updated_at"), rs.getInt("survey_id"), rs.getString("chapter_number"), rs.getString("chapter_title"));
                    return Optional.of(chapter);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateChapter(Chapter chapter) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateChapter'");
    }

    @Override
    public void deleteChapter(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteChapter'");
    }

    @Override
    public List<Chapter> findChapterBySurvey(int surveyId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findChapterBySurvey'");
    }

    @Override
    public List<ChapterDto> findChapterDto(int id) {
        String sql = "SELECT * FROM chapter WHERE survey_id = ? ";
        List<ChapterDto> chapterList = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    chapterList.add(new ChapterDto(
                        rs.getInt("id"),
                        rs.getString("chapter_title"),
                        rs.getString("chapter_number"),
                        questionRepository.findQuestionDto(rs.getInt("id"))));  
                }
                  return chapterList;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chapterList;
    }

}
