package com.example.ecommerce.service;

import com.example.ecommerce.DTO.response.OrderInfoResponseDTO;
import com.example.ecommerce.DTO.response.OrderItemResponseDTO;
import com.example.ecommerce.DTO.response.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    @Value("${spring.mail.username}")
    private String sender;
    @Autowired
    public EmailService(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    public void sendSimpleMail(String name)
    {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            message.setSubject("NEW USER ACCOUNT");
            message.setRecipients(MimeMessage.RecipientType.TO, name);
            message.setFrom(sender);
            message.setText("An user have been registered!");
            javaMailSender.send(message);
            System.out.println("Mail sent");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void sendHtmlEmail(String receiver, UserResponseDTO user, List<OrderItemResponseDTO> orderItems, OrderInfoResponseDTO orderInfo){
        try {
            Context context = new Context();
            context.setVariable("user",user);
            context.setVariable("orderItems", orderItems);
            context.setVariable("orderInfo", orderInfo);

            String text = templateEngine.process("orderMail.html",context);

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setPriority(1);
            helper.setSubject("NEW ORDER HAVE BEEN PROCESSED");
            helper.setFrom(sender);
            helper.setTo(receiver);
            helper.setText(text, true);
            javaMailSender.send(message);
            System.out.println("Mail sent");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
