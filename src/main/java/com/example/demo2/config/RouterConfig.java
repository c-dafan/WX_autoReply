package com.example.demo2.config;

import com.example.demo2.handler.*;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RouterConfig {
    @Autowired
    private WxMpService wxMpService;
    @Autowired
    private MsgHandler msgHandler;
    @Autowired
    private ImageHander imageHander;
    @Autowired
    private VideoHandler videoHandler;
    @Autowired
    private UpHandler upHandler;
    @Autowired
    private ModelHandler modelHandler;
    @Bean
    public WxMpMessageRouter router(){
        WxMpMessageRouter new_router = new WxMpMessageRouter(wxMpService);
//        new_router.rule().async(false).rContent("^哈哈[:：]*[\\s\\S]*").handler(msgHandler).end();
//        new_router.rule().async(false).rContent("^上传[:：]*[\\s\\S]*").handler(upHandler).end();
        new_router.rule().async(false).msgType("image").handler(imageHander).end();
        new_router.rule().async(false).msgType("video").handler(videoHandler).end();
//        new_router.rule().rContent("^查询[:：]?[\\s\\S]*").handler(modelHandler).end();
        return new_router;
    }
}
