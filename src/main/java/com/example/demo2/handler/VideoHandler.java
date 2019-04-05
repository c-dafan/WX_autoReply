package com.example.demo2.handler;

import com.example.demo2.handler.VO.Video2Rabbit;
import com.example.demo2.sender.RabbitmqSender;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
@Slf4j
@Component
public class VideoHandler implements WxMpMessageHandler {
    @Autowired
    private RabbitmqSender rabbitmqSender;
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
        log.info("进入handler");
        Video2Rabbit video2Rabbit = new Video2Rabbit();
        video2Rabbit.setAccess_token(wxMpService.getAccessToken());
        video2Rabbit.setToUser(wxMessage.getFromUser());
        video2Rabbit.setMedia_id(wxMessage.getMediaId());
        try {
            rabbitmqSender.send_video(video2Rabbit);
        } catch (JsonProcessingException e) {
            log.error("发送失败");
        }
        return null;
    }
}
