package com.thi.cuoiky.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmailController {

    @Autowired
    private JavaMailSender emailSender;

    @GetMapping("/guiMail")
    public String showForm() {
        return "/gui-mail/mail";
    }
    
    @GetMapping("/guiTC")
    public String showFormTC() {
        return "/gui-mail/thanh-cong";
    }
    
    @PostMapping("/sendEmail")
    public String sendEmail(@RequestParam("email") String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Thông báo khuyến mãi");
        message.setText("Chúc mừng! Bạn đã đăng ký nhận thông báo ưu đãi, khuyến mãi, "
        + "các chương trình đặc biệt và phim mới của GCV.");
        message.setFrom("runchtyhotelsresortssanfr@gmail.com");

        emailSender.send(message);
        return "redirect:/guiTC";
    }
}