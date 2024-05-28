package com.thi.cuoiky.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thi.cuoiky.entities.Phim;
import com.thi.cuoiky.services.PhimService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/khach-hang")
public class KhachHangController {

    @Autowired
    private PhimService phimService;

    @GetMapping("/home")
    public String adminHome(HttpSession session, Model model) {
        String greetingMessage = (String) session.getAttribute("greetingMessage");
        Integer maNguoiDung = (Integer) session.getAttribute("maNguoiDung"); // Lấy maNguoiDung từ session
        List<Phim> danhSachPhim = phimService.getAllPhim(); // Lấy danh sách phim
        model.addAttribute("greetingMessage", greetingMessage);
        model.addAttribute("danhSachPhim", danhSachPhim); // Thêm danh sách phim vào model
        model.addAttribute("maNguoiDung", maNguoiDung); // Thêm maNguoiDung vào model
        return "khach-hang/home"; // Trả về trang khach-hang/home.html
    }
}
