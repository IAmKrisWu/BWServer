package com.junxi.baiweiserver.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.junxi.baiweiserver.model.Hr;
import com.junxi.baiweiserver.model.Message;
import com.junxi.baiweiserver.model.OnLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
public class WSController {
//    消息模版
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

//    principal是当前登录的对象。前端将信息发送到/ws/chat。然后由simpleMessageingTemplate进行转发。message是前端发送过来的信息。前端订阅/queue/chat接收消息。
    @MessageMapping("/ws/chat")
    public void chat(Principal principal, @RequestBody Message message) throws JsonProcessingException {
        System.out.println(message);
//        该消息是由发送的
        message.setFrom(principal.getName());
//        转为json
        ObjectMapper om = new ObjectMapper();
        String msg = om.writeValueAsString(message);
//        messagingTemplate和springsecurity是可以无缝连接的。当我们设置发送的对象，messagingTemplate会到当前登录的用户中寻找发送的对象，
//        如果用户名匹配上，则会进行发送。
        simpMessagingTemplate.convertAndSendToUser(message.getTo(),"/queue/chat",msg);
    }
//    保存在线的用户
    List<OnLine> onLines = new LinkedList<>();

    @MessageMapping("/ws/online")
    @SendTo("/topic/online")
    public String handleNF(@RequestBody OnLine onLine) throws IOException {
        if(onLine.isIson()==true){
            onLines.add(onLine);
        }else {
            for (OnLine online : onLines) {
                Hr user = online.getUser();
                if(user.getId()==onLine.getUser().getId()){
                    onLines.remove(online);
                }
            }
        }
        ObjectMapper om = new ObjectMapper();
        String s = om.writeValueAsString(onLines);
        System.out.println(s);
        return s;
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
//        OnLine onLine = mapper.readValue(json.toString(), OnLine.class);
//
//        System.out.println(onLine);
    }
}
