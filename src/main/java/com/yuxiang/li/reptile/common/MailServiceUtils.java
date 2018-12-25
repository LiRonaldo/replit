package com.yuxiang.li.reptile.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by yuxiang.li on 2018/12/3.
 * 发送邮件
 */
@Component
public class MailServiceUtils {
    private JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    @Autowired
    private Environment env;
    private static String auth;
    private static String host;
    private static String protocol;
    private static Integer port;
    private static String authName;
    private static String password;
    private static String charset;
    private static Boolean isSSL;
    private static String timeout;
    private static String from;

    @PostConstruct
    public void initParam() {
        auth = env.getProperty("mail.smtp.auth");
        host = env.getProperty("mail.host");
        protocol = env.getProperty("mail.transport.protocol");
        port = env.getProperty("mail.smtp.port", Integer.class);
        authName = env.getProperty("mail.auth.name");
        password = env.getProperty("mail.auth.password");
        charset = env.getProperty("mail.send.charset");
        isSSL = env.getProperty("mail.is.ssl", Boolean.class);
        timeout = env.getProperty("mail.smtp.timeout");
        from = env.getProperty("mail.from");
    }


    public boolean sendEmail(String[] toUsers, String content) {
        try {
            System.out.println("-----开启邮件发送------");
            MimeMessage mimeMailMessage = mailSender.createMimeMessage();
            mailSender.setHost(host);
            mailSender.setPassword(password);
            mailSender.setUsername(authName);
            mailSender.setPort(port);
            mailSender.setDefaultEncoding(charset);
            Properties properties = new Properties();
            properties.setProperty("mail.smtp.auth", auth);//开启认证
            properties.setProperty("mail.smtp.timeout", timeout);//设置链接超时
            properties.setProperty("mail.smtp.port", Integer.toString(port));//设置端口
            properties.put("mail.smtp.ssl.enable", "true");
            properties.setProperty("mail.smtp.socketFactory.port", Integer.toString(port));//设置ssl端口
            properties.setProperty("mail.smtp.socketFactory.fallback", "false");
            properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            mailSender.setJavaMailProperties(properties);
            MimeMessageHelper helper = new MimeMessageHelper(mimeMailMessage, true);
            helper.setFrom(from);
            helper.setTo(toUsers);
            helper.setSubject(Constant.SUBJECT);
            helper.setText(content);
            mailSender.setProtocol(protocol);
            mailSender.send(mimeMailMessage);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return true;
    }


}
