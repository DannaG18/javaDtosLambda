package com.fixing.domain.dto;

import java.util.List;

public class QuestionDto {
    private int id;
    private String name;
    // private List<ResponseOptionsDto> childResponseOptions;

    
    public QuestionDto(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public QuestionDto() {
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    
}
