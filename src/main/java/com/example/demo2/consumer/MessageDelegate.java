package com.example.demo2.consumer;

import com.example.demo2.consumer.VO.Answer2User;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class MessageDelegate {
    @Autowired
    private WxMpService wxMpService;
    public void consumeMessage(Answer2User answer2User) {
        log.info("成功:{}",answer2User);
        WxMpKefuMessage message = WxMpKefuMessage.TEXT().toUser(answer2User.getToUser()).content(answer2User.getAnswer()).build();
        try {
            wxMpService.getKefuService().sendKefuMessage(message);
        } catch (WxErrorException e) {
            log.error("发送失败:{}",e);
            try {
                wxMpService.getKefuService().sendKefuMessage(message);
            } catch (WxErrorException e1) {
                log.error("发送再次失败");
            }
        }
    }
}
