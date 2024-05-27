package com.thi.cuoiky.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thi.cuoiky.entities.NguoiDung;
import com.thi.cuoiky.entities.VaiTro;
import com.thi.cuoiky.repositories.INguoiDung;

@Controller
public class RegisterController {

    @Autowired
    private INguoiDung nguoiDungRepository;

    @GetMapping("/register")
    public String showRegisterForm() {
        return "dang-ky/register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, 
                               @RequestParam String password, 
                               @RequestParam String confirmPassword,
                               @RequestParam String fullname, 
                               @RequestParam String email,
                               @RequestParam(required = false) String diachi,
                               @RequestParam(required = false) String sodienthoai,
                               @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date ngaysinh,
                               Model model) {
        if (nguoiDungRepository.findByTenDangNhap(username) != null) {
            model.addAttribute("error", "Tên đăng nhập đã tồn tại");
            return "dang-ky/register";
        }

        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Mật khẩu và xác nhận mật khẩu không khớp");
            return "dang-ky/register";
        }

        NguoiDung newUser = new NguoiDung();
        newUser.setTenDangNhap(username);
        newUser.setMatKhau(password);
        newUser.setHoTen(fullname);
        newUser.setEmail(email);
        newUser.setDiaChi(diachi);
        newUser.setSoDienThoai(sodienthoai);
        newUser.setNgaySinh(ngaysinh);
        
        // Thiết lập vai trò mặc định là khách hàng với mã vai trò là 3
        VaiTro vaiTro = new VaiTro();
        vaiTro.setMaVaiTro(3);
        newUser.setVaiTro(vaiTro);
        
        nguoiDungRepository.save(newUser);

        return "redirect:/login";
    }
}