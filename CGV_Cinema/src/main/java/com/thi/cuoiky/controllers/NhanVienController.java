package com.thi.cuoiky.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/nhan-vien")
public class NhanVienController {

    @GetMapping("/home")
    public String nhanVienHome(Model model) {
        // Bạn có thể thêm logic để lấy tên đăng nhập của nhân viên
        // model.addAttribute("greetingMessage", "Xin chào, TênNhânViên!");
        return "nhan-vien/home"; // Trả về trang nhan-vien/home.html
    }
}
