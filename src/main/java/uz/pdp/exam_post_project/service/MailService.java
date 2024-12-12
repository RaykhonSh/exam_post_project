package uz.pdp.exam_post_project.service;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.SneakyThrows;

import java.util.Properties;

public class MailService {
    @SneakyThrows
    public static void send(String code, String email) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host","sandbox.smtp.mailtrap.io");
        properties.put("mail.smtp.port","2525");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.auth","true");

        String userName="cf122b9aeea3ca";
        String password="87152e114bb3e4";

        Session session=getSession(properties,userName,password);

        MimeMessage message = new MimeMessage(
                session
        );
        message.setSubject("This is code");
        message.setText(code);
        message.setFrom(new InternetAddress(userName));
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(email));

        Transport.send(message);
        System.out.println("success");
    }


    private static Session getSession(Properties properties, String userName, String password) {
        return Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName,password);
            }
        });
    }
}
