package com.example.demo2.handler;

import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.material.WxMediaImgUploadResult;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutImageMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Map;

@Component
public class UpHandler implements WxMpMessageHandler {
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        File file = new File("/root/img/1.jpg");
        WxMediaUploadResult res = wxMpService.getMaterialService().mediaUpload("image",file);
        String mediaId = res.getMediaId();
        WxMpXmlOutImageMessage msg = WxMpXmlOutImageMessage.IMAGE().toUser(wxMpXmlMessage.getFromUser()).fromUser(wxMpXmlMessage.getToUser()).mediaId(mediaId).build();
        return msg;
    }
}
