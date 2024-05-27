package com.thi.cuoiky.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/home")
    public String adminHome(Model model) {
        model.addAttribute("greetingMessage", "Xin chào, Quản Trị Viên!");
        return "admin/home"; // Trả về trang admin/home.html
    }
}