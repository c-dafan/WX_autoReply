package com.example.demo2.handler.VO;

import lombok.Data;

@Data
public class Video2Rabbit {
    private String toUser;
    private String access_token;
    private String media_id;

    @Override
    public String toString() {
        return "Video2Rabbit{" +
                "toUser='" + toUser + '\'' +
                ", access_token='" + access_token + '\'' +
                ", media_id='" + media_id + '\'' +
                '}';
    }
}
