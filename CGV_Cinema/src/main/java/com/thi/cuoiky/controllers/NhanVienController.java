package com.thi.cuoiky.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/nhan-vien")
public class NhanVienController {

    @GetMapping("/home")
    public String nhanVienHome(HttpSession session, Model model) {
        String greetingMessage = (String) session.getAttribute("greetingMessage");
        model.addAttribute("greetingMessage", greetingMessage);
        return "nhan-vien/home"; // Trả về trang nhan-vien/home.html
    }
}

