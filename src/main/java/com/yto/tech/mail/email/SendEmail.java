package com.yto.tech.mail.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Component
public class SendEmail {
    private static final Logger logger = LoggerFactory.getLogger(SendEmail.class);

    private static Properties props = null;
    @Value("${email.host}")
    private String emailHost;
    @Value("${email.account}")
    private String account;
    @Value("${email.pwd}")
    private String pwd;

    public SendEmail(){
        logger.info("SendEmail cons");
    }

    @PostConstruct
    public void init(){
        props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", emailHost);
        props.setProperty("mail.smtp.auth", "true");

        logger.info("########### email config ###############");
        logger.info("####        emailHost : {} ",emailHost);
        logger.info("####        account : {}",account);
        logger.info("####        pwd : {}","hidden");
        logger.info("########### email config ###############");
    }

    public MimeMessage createMimeMessage(String subject, String content, String tos)
            throws Exception{
        Session session = Session.getDefaultInstance(props);
        String[] targets = tos.split(",");
        InternetAddress[] ia = new InternetAddress[targets.length];
        for (int i = 0; i < targets.length; i++) {
            ia[i] = new InternetAddress(targets[i]);
        }
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(account, "创新技术部-CAT监控", "UTF-8"));
        message.setRecipients(MimeMessage.RecipientType.TO, ia);
        message.setSubject(subject, "UTF-8");
        message.setContent(content, "text/html;charset=UTF-8");
        message.setSentDate(new Date());
        message.saveChanges();

        Transport transport = session.getTransport();
        transport.connect(account, pwd);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
        return message;
    }

    public static void main(String[] args)
            throws Exception
    {}
}
