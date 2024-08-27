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
import com.fixing.domain.dto.SurveyDto;
import com.fixing.domain.entity.Survey;
import com.fixing.domain.service.SurveyService;

public class SurveyRepository implements SurveyService{
    private Connection connection;
    private ChapterRepository chapterRepository;


    public SurveyRepository() {
        this.chapterRepository = new ChapterRepository();
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
    public void createSurvey(Survey survey) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createSurvey'");
    }

    @Override
    public Optional<Survey> findSurvey(int id) {
        String sql = "SELECT * FROM surveys WHERE id = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    Survey survey = new Survey(rs.getInt("id"), rs.getDate("created_at"), rs.getDate("updated_at"), rs.getString("description"), rs.getString("name"));
                    return Optional.of(survey);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Survey> findSurveyByName(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findSurveyByName'");
    }

    @Override
    public void updateSurvey(Survey survey) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateSurvey'");
    }

    @Override
    public void deleteSurvey(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteSurvey'");
    }

    @Override
    public void getAllSurvey(List<Survey> names) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllSurvey'");
    }

    @Override
    public Optional<SurveyDto> findSurveyDto(int id) {
        String sql = "SELECT * FROM surveys WHERE id = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) { 
                    SurveyDto surveyDto = new SurveyDto(
                        rs.getInt("id"),
                        rs.getString("name"),
                        chapterRepository.findChapterDto(rs.getInt("id")));
                    return Optional.of(surveyDto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
