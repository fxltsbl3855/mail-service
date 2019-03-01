package com.yto.tech.mail.controller;

import com.yto.tech.mail.Contant;
import com.yto.tech.mail.email.SendEmail;
import com.yto.tech.mail.wx.WXSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WebController {
    private static final Logger logger = LoggerFactory.getLogger(WebController.class);
    private static String istest = "false";


    @Autowired
    SendEmail sendEmail;

    @Autowired
    WXSender wxSender;

    @RequestMapping(value = "/sendEmail")
    public String sendEmail(@RequestParam("content") String content, @RequestParam("subject") String subject, @RequestParam("tos") String tos) {
        logger.info("sendEmail invoke......subject=" + subject + ",tos=" + tos + ",content=" + content);
        content = Contant.alertContentAddCatEnv(content);
        try {
            long s = System.currentTimeMillis();
            if(istest.equals("false")) {
                sendEmail.createMimeMessage(subject, content, tos);
            }else{
                logger.info("test...................test");
            }
            logger.info("sendEmail over , time= {}ms",(System.currentTimeMillis()-s));
            return "sendEmail success";
        } catch (Exception e) {
            logger.error("sendEmail error", e);
            return e.getMessage();
        }

    }

    @RequestMapping(value = "/sendWeixin")
    public String sendWeixin(@RequestParam("content") String content, @RequestParam("subject") String subject, @RequestParam("tos") String tos) {
        logger.info("sendWeixin invoke......subject=" + subject + ",tos=" + tos + ",content=" + content);
        try {
            if(istest.equals("false")) {
                //wxSender.sendWXPost(content);
            }else{
                logger.info("test...................test");
            }
            return "sendWeixin success";
        } catch (Exception e) {
            logger.error("sendWeixin error", e);
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/sendSNS")
    public String sendSNS(@RequestParam("content") String content, @RequestParam("subject") String subject, @RequestParam("tos") String tos) {
        logger.info("sendSNS invoke......subject=" + subject + ",tos=" + tos + ",content=" + content);
        try {

            if(istest.equals("false")) {
                //TODO
            }else{
                logger.info("test...................test");
            }
            return "sendSNS success";
        } catch (Exception e) {
            logger.error("sendSNS error", e);
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/c")
    public String c(@RequestParam("flag") String flag) {
        logger.info("c invoke.....istest=" + istest);

            if(flag.equals("false")||flag.equals("true")) {
                istest = flag;
            }else{
                logger.error("flag value is error, flag = {}" , flag);
            }
            return "param flag is "+flag+", current istest is "+istest;

    }

}
