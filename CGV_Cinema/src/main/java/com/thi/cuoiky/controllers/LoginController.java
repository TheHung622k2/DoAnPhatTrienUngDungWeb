package com.thi.cuoiky.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thi.cuoiky.entities.NguoiDung;
import com.thi.cuoiky.services.NguoiDungService;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;

@Controller
public class LoginController {

    @Autowired
    private NguoiDungService nguoiDungService;

    @GetMapping("/login")
    public String login() {
        return "dang-nhap/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("tenDangNhap") String tenDangNhap, @RequestParam("matKhau") String matKhau, Model model) {
        NguoiDung nguoiDung = nguoiDungService.getNguoiDungByTenDangNhap(tenDangNhap);
        if (nguoiDung != null && nguoiDung.getMatKhau().equals(matKhau)) {
            String greetingMessage = "";
            switch (nguoiDung.getVaiTro().getTenVaiTro()) {
                case "Admin":
                    greetingMessage = "Xin chào, Quản Trị Viên!";
                    model.addAttribute("greetingMessage", greetingMessage);
                    return "admin/home"; // Chuyển hướng về trang của admin
                case "Nhân Viên":
                    greetingMessage = "Xin chào, " + nguoiDung.getHoTen() + "!";
                    model.addAttribute("greetingMessage", greetingMessage);
                    return "nhan-vien/home"; // Chuyển hướng về trang của nhân viên
                case "Khách Hàng":
                    greetingMessage = "Xin chào, " + nguoiDung.getHoTen() + "!";
                    model.addAttribute("greetingMessage", greetingMessage);
                    return "khach-hang/home"; // Chuyển hướng về trang của khách hàng
            }
        } else {
            model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng");
            return "dang-nhap/login";
        }
        return "dang-nhap/login";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
