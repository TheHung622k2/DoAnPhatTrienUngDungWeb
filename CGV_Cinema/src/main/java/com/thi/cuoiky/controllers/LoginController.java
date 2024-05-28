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
    public String login(@RequestParam("tenDangNhap") String tenDangNhap, @RequestParam("matKhau") String matKhau, Model model, HttpSession session) {
        NguoiDung nguoiDung = nguoiDungService.getNguoiDungByTenDangNhap(tenDangNhap);
        if (nguoiDung != null && nguoiDung.getMatKhau().equals(matKhau)) {
            String greetingMessage = "";
            String userRole = nguoiDung.getVaiTro().getTenVaiTro();
            switch (userRole) {
                case "Admin":
                    greetingMessage = "Xin chào, Quản Trị Viên!";
                    break;
                case "Nhân Viên":
                    greetingMessage = "Xin chào, " + nguoiDung.getTenDangNhap() + "!";
                    break;
                case "Khách Hàng":
                    greetingMessage = "Xin chào, " + nguoiDung.getHoTen() + "!";
                    break;
            }
            model.addAttribute("greetingMessage", greetingMessage);
            session.setAttribute("greetingMessage", greetingMessage);
            session.setAttribute("userRole", userRole);
            session.setAttribute("maNguoiDung", nguoiDung.getMaNguoiDung());
            return "redirect:/" + (userRole.equals("Admin") ? "admin/home" : userRole.equals("Nhân Viên") ? "nhan-vien/home" : "khach-hang/home");
        } else {
            model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng");
            return "dang-nhap/login";
        }
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
