package com.example.demo2.consumer.VO;

import lombok.Data;

@Data
public class Answer2User {
    private String toUser;
    private String answer;

    @Override
    public String toString() {
        return "Answer2User{" +
                "toUser='" + toUser + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
