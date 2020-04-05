package com.davina.sms.listener;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.davina.sms.util.SmsUtil;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ClassName SmsListener
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/3/24 21:12
 * @Version 1.0
 */
@Component
@RabbitListener(queues = "sms")
@ConfigurationProperties(prefix = "aliyun.sms")
public class SmsListener {

    @Autowired
    private SmsUtil smsUtil;

    private String template_code;

    private String signName;

    public String getTemplate_code() {
        return template_code;
    }

    public void setTemplate_code(String template_code) {
        this.template_code = template_code;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    @RabbitHandler
    public void sendSMS(Map<String,String> message, Channel channel, Message msg){
        System.out.println("mobile:"+message.get("mobile"));
        System.out.println("code:"+message.get("code"));

        try{
            SendSmsResponse sendSmsResponse = smsUtil.sendSms(message.get("mobile"), template_code, signName, "{\"code\":\"" + message.get("code") + "\"}");

            if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")){
                try {
                    channel.basicAck(msg.getMessageProperties().getDeliveryTag(),false);
                }catch (Exception e){
                    e.getMessage();
                }
                System.out.println("短信发送成功");
            }else {
                System.out.println("短信发送失败："+sendSmsResponse.getCode());
            }
        }catch (ClientException e){
            e.printStackTrace();
        }
    }
}
