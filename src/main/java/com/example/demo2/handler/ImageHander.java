package com.example.demo2.handler;

import com.example.demo2.handler.VO.Image2Rabbit;
import com.example.demo2.sender.RabbitmqSender;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutImageMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class ImageHander implements WxMpMessageHandler {
    @Autowired
    private RabbitmqSender rabbitmqSender;
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        WxMpXmlOutImageMessage msg = WxMpXmlOutImageMessage.IMAGE().toUser(wxMpXmlMessage.getFromUser()).fromUser(wxMpXmlMessage.getToUser())
                .mediaId(wxMpXmlMessage.getMediaId()).build();
//        File file = wxMpService.getMaterialService().mediaDownload(wxMpXmlMessage.getMediaId());
        Image2Rabbit rabbit = new Image2Rabbit();
        rabbit.setDownUrl(wxMpXmlMessage.getPicUrl());
        rabbit.setToUser(wxMpXmlMessage.getFromUser());
        try {
            rabbitmqSender.send_ocr(rabbit);
        } catch (JsonProcessingException e) {
            log.error("转化失败");
        }
        return msg;
    }
}
