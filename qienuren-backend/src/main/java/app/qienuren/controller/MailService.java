package app.qienuren.controller;

import app.qienuren.model.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Component
public class MailService {

    private JavaMailSender javaMailSender;


    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    void sendEmail(Mail mail) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(mail.getEmailTo());
        msg.setSubject(mail.getSubject());
        msg.setText(mail.getText());
        javaMailSender.send(msg);
    }
}