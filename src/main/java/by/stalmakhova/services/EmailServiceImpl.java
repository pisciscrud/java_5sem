package by.stalmakhova.services;

import by.stalmakhova.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl {

    private final JavaMailSender javaMailSender;
    private final UserServiceImpl userService;

    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender, UserServiceImpl userService) {
        this.javaMailSender = javaMailSender;
        this.userService = userService;
    }

    public void sendEmail ( String toEmail,String subject , String body){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);
        mailMessage.setFrom(sender);

        javaMailSender.send(mailMessage);
    }

    public void sendEmailToUser(String subject, String body, User user) {
            sendEmail(user.getEmail(), subject, body);
        }
    }


