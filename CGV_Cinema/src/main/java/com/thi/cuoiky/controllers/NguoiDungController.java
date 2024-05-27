package com.thi.cuoiky.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thi.cuoiky.entities.NguoiDung;
import com.thi.cuoiky.entities.VaiTro;
import com.thi.cuoiky.services.NguoiDungService;
import com.thi.cuoiky.services.VaiTroService;

@Controller
@RequestMapping("/nguoi-dung")
public class NguoiDungController {

    @Autowired
    private NguoiDungService nguoiDungService;

    @Autowired
    private VaiTroService vaiTroService;

    @GetMapping
    public String getAllNguoiDung(Model model) {
        List<NguoiDung> nguoiDungList = nguoiDungService.getAllNguoiDung();
        model.addAttribute("nguoiDungList", nguoiDungList);
        return "nguoi-dung/list"; // Trả về tên của Thymeleaf template để hiển thị danh sách người dùng
    }

    @GetMapping("/add")
    public String showAddNguoiDungForm(Model model) {
        NguoiDung nguoiDung = new NguoiDung();
        List<VaiTro> vaiTroList = vaiTroService.getAllVaiTro();
        model.addAttribute("nguoiDung", nguoiDung);
        model.addAttribute("vaiTroList", vaiTroList);
        return "nguoi-dung/add"; // Trả về tên của Thymeleaf template để hiển thị form thêm người dùng
    }

    @PostMapping("/add")
    public String addNguoiDung(@ModelAttribute("nguoiDung") NguoiDung nguoiDung) {
        nguoiDungService.saveNguoiDung(nguoiDung);
        return "redirect:/nguoi-dung"; // Chuyển hướng về trang danh sách người dùng sau khi thêm thành công
    }

    @GetMapping("/edit/{id}")
    public String showEditNguoiDungForm(@PathVariable("id") int id, Model model) {
        NguoiDung nguoiDung = nguoiDungService.getNguoiDungById(id);
        List<VaiTro> vaiTroList = vaiTroService.getAllVaiTro();
        if (nguoiDung != null) {
            model.addAttribute("nguoiDung", nguoiDung);
            model.addAttribute("vaiTroList", vaiTroList);
            return "nguoi-dung/edit"; // Trả về tên của Thymeleaf template để hiển thị form chỉnh sửa người dùng
        } else {
            return "redirect:/nguoi-dung"; // Chuyển hướng về trang danh sách người dùng nếu không tìm thấy người dùng với id đã cho
        }
    }

    @PostMapping("/edit/{id}")
    public String editNguoiDung(@PathVariable("id") int id, @ModelAttribute("nguoiDung") NguoiDung nguoiDung) {
        nguoiDung.setMaNguoiDung(id);
        nguoiDungService.saveNguoiDung(nguoiDung);
        return "redirect:/nguoi-dung"; // Chuyển hướng về trang danh sách người dùng sau khi chỉnh sửa thành công
    }

    @GetMapping("/delete/{id}")
    public String deleteNguoiDung(@PathVariable("id") int id) {
        nguoiDungService.deleteNguoiDung(id);
        return "redirect:/nguoi-dung"; // Chuyển hướng về trang danh sách người dùng sau khi xóa thành công
    }

    @GetMapping("/search")
    public String searchNguoiDung(@RequestParam("tenDangNhap") String tenDangNhap, Model model) {
        NguoiDung nguoiDung = nguoiDungService.getNguoiDungByTenDangNhap(tenDangNhap);
        model.addAttribute("nguoiDung", nguoiDung);
        return "nguoi-dung/search-result"; // Trả về tên của Thymeleaf template để hiển thị kết quả tìm kiếm
    }
}
