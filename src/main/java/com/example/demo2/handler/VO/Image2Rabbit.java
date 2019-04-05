package com.example.demo2.handler.VO;

import lombok.Data;

@Data
public class Image2Rabbit {
    private String downUrl;
    private String toUser;

    @Override
    public String toString() {
        return "Image2Rabbit{" +
                "downUrl='" + downUrl + '\'' +
                ", toUser='" + toUser + '\'' +
                '}';
    }
}
