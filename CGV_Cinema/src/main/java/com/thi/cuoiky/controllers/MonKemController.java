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

import com.thi.cuoiky.entities.LoaiDoKemThem;
import com.thi.cuoiky.entities.MonKem;
import com.thi.cuoiky.services.LoaiDoKemThemService;
import com.thi.cuoiky.services.MonKemService;

@Controller
@RequestMapping("/mon-kem")
public class MonKemController {

    @Autowired
    private MonKemService monKemService;

    @Autowired
    private LoaiDoKemThemService loaiDoKemThemService;

    @GetMapping
    public String getAllMonKem(Model model) {
        List<MonKem> monKemList = monKemService.getAllMonKem();
        model.addAttribute("monKemList", monKemList);
        return "mon-kem/list"; // Trả về tên của Thymeleaf template để hiển thị danh sách món kem
    }

    @GetMapping("/add")
    public String showAddMonKemForm(Model model) {
        MonKem monKem = new MonKem();
        List<LoaiDoKemThem> loaiDoKemThemList = loaiDoKemThemService.getAllLoaiDoKemThem();
        model.addAttribute("monKem", monKem);
        model.addAttribute("loaiDoKemThemList", loaiDoKemThemList);
        return "mon-kem/add"; // Trả về tên của Thymeleaf template để hiển thị form thêm món kem
    }

    @PostMapping("/add")
    public String addMonKem(@ModelAttribute("monKem") MonKem monKem) {
        monKemService.saveMonKem(monKem);
        return "redirect:/mon-kem"; // Chuyển hướng về trang danh sách món kem sau khi thêm thành công
    }

    @GetMapping("/edit/{id}")
    public String showEditMonKemForm(@PathVariable("id") int id, Model model) {
        MonKem monKem = monKemService.getMonKemById(id);
        List<LoaiDoKemThem> loaiDoKemThemList = loaiDoKemThemService.getAllLoaiDoKemThem();
        if (monKem != null) {
            model.addAttribute("monKem", monKem);
            model.addAttribute("loaiDoKemThemList", loaiDoKemThemList);
            return "mon-kem/edit"; // Trả về tên của Thymeleaf template để hiển thị form chỉnh sửa món kem
        } else {
            return "redirect:/mon-kem"; // Chuyển hướng về trang danh sách món kem nếu không tìm thấy món kem với id đã cho
        }
    }

    @PostMapping("/edit/{id}")
    public String editMonKem(@PathVariable("id") int id, @ModelAttribute("monKem") MonKem monKem) {
        monKem.setMaMonKem(id);
        monKemService.saveMonKem(monKem);
        return "redirect:/mon-kem"; // Chuyển hướng về trang danh sách món kem sau khi chỉnh sửa thành công
    }

    @GetMapping("/delete/{id}")
    public String deleteMonKem(@PathVariable("id") int id) {
        monKemService.deleteMonKem(id);
        return "redirect:/mon-kem"; // Chuyển hướng về trang danh sách món kem sau khi xóa thành công
    }
}