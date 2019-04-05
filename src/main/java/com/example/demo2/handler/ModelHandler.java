package com.example.demo2.handler;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class ModelHandler implements WxMpMessageHandler {
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        WxMpTemplateMessage msg = WxMpTemplateMessage.builder().toUser(wxMpXmlMessage.getFromUser()).templateId("GIpQzLrjx0c1RwTBxF91r6RPsbyicZWy19dqPMpJcSY").url("www.baidu.com").build();
        msg.addWxMpTemplateData(new WxMpTemplateData("result","测试成功","#173177"));
        msg.addWxMpTemplateData(new WxMpTemplateData("money","5050","#173177"));
        log.info("{}" ,msg.toJson());
        wxMpService.getTemplateMsgService().sendTemplateMsg(msg);
        return null;
    }
}
