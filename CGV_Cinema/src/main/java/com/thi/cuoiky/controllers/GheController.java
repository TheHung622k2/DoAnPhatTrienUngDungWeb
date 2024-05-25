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

import com.thi.cuoiky.entities.Ghe;
import com.thi.cuoiky.services.GheService;

@Controller
@RequestMapping("/ghe")
public class GheController {

    @Autowired
    private GheService gheService;

    @GetMapping
    public String getAllGhe(Model model) {
        List<Ghe> gheList = gheService.getAllGhe();
        model.addAttribute("gheList", gheList);
        return "ghe/list"; // Trả về tên của Thymeleaf template để hiển thị danh sách ghế
    }

    @GetMapping("/add")
    public String showAddGheForm(Model model) {
        Ghe ghe = new Ghe();
        model.addAttribute("ghe", ghe);
        return "ghe/add"; // Trả về tên của Thymeleaf template để hiển thị form thêm ghế
    }

    @PostMapping("/add")
    public String addGhe(@ModelAttribute("ghe") Ghe ghe) {
        gheService.saveGhe(ghe);
        return "redirect:/ghe"; // Chuyển hướng về trang danh sách ghế sau khi thêm thành công
    }

    @GetMapping("/edit/{id}")
    public String showEditGheForm(@PathVariable("id") int id, Model model) {
        Ghe ghe = gheService.getGheById(id);
        if (ghe != null) {
            model.addAttribute("ghe", ghe);
            return "ghe/edit"; // Trả về tên của Thymeleaf template để hiển thị form chỉnh sửa ghế
        } else {
            return "redirect:/ghe"; // Chuyển hướng về trang danh sách ghế nếu không tìm thấy ghế với id đã cho
        }
    }

    @PostMapping("/edit/{id}")
    public String editGhe(@PathVariable("id") int id, @ModelAttribute("ghe") Ghe ghe) {
        ghe.setMaGhe(id);
        gheService.saveGhe(ghe);
        return "redirect:/ghe"; // Chuyển hướng về trang danh sách ghế sau khi chỉnh sửa thành công
    }

    @GetMapping("/delete/{id}")
    public String deleteGhe(@PathVariable("id") int id) {
        gheService.deleteGhe(id);
        return "redirect:/ghe"; // Chuyển hướng về trang danh sách ghế sau khi xóa thành công
    }
}