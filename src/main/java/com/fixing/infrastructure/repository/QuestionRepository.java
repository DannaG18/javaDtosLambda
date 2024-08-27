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

import com.fixing.domain.dto.QuestionDto;
import com.fixing.domain.entity.Question;
import com.fixing.domain.service.QuestionService;

public class QuestionRepository implements QuestionService {
    private Connection connection;

    public QuestionRepository() {
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
    public Optional<Question> findQuestion(int id) {
        String sql = "SELECT * FROM questions WHERE id = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    Question question = new Question(rs.getInt("id"), rs.getInt("chapter_id"), rs.getDate("created_at"), rs.getDate("updated_at"), rs.getString("question_number"), rs.getString("response_type"), rs.getString("comment_question"), rs.getString("question_text"));
                    return Optional.of(question);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<QuestionDto> findQuestionDto(int id) {
        String sql = "SELECT * FROM questions WHERE chapter_id = ? ";
        List<QuestionDto> questionDto = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    questionDto.add(new QuestionDto(
                        rs.getInt("id"),
                        rs.getString("question_text"))); 
                }
                return questionDto;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questionDto;
    }

}
