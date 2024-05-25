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
import com.thi.cuoiky.services.LoaiDoKemThemService;

@Controller
@RequestMapping("/loai-do-kem-them")
public class LoaiDoKemThemController {

    @Autowired
    private LoaiDoKemThemService loaiDoKemThemService;

    @GetMapping
    public String getAllLoaiDoKemThem(Model model) {
        List<LoaiDoKemThem> loaiDoKemThemList = loaiDoKemThemService.getAllLoaiDoKemThem();
        model.addAttribute("loaiDoKemThemList", loaiDoKemThemList);
        return "loai-do-kem-them/list"; // Trả về tên của Thymeleaf template để hiển thị danh sách loại đồ kèm thêm
    }

    @GetMapping("/add")
    public String showAddLoaiDoKemThemForm(Model model) {
        LoaiDoKemThem loaiDoKemThem = new LoaiDoKemThem();
        model.addAttribute("loaiDoKemThem", loaiDoKemThem);
        return "loai-do-kem-them/add"; // Trả về tên của Thymeleaf template để hiển thị form thêm loại đồ kèm thêm
    }

    @PostMapping("/add")
    public String addLoaiDoKemThem(@ModelAttribute("loaiDoKemThem") LoaiDoKemThem loaiDoKemThem) {
        loaiDoKemThemService.saveLoaiDoKemThem(loaiDoKemThem);
        return "redirect:/loai-do-kem-them"; // Chuyển hướng về trang danh sách loại đồ kèm thêm sau khi thêm thành công
    }

    @GetMapping("/edit/{id}")
    public String showEditLoaiDoKemThemForm(@PathVariable("id") int id, Model model) {
        LoaiDoKemThem loaiDoKemThem = loaiDoKemThemService.getLoaiDoKemThemById(id);
        if (loaiDoKemThem != null) {
            model.addAttribute("loaiDoKemThem", loaiDoKemThem);
            return "loai-do-kem-them/edit"; // Trả về tên của Thymeleaf template để hiển thị form chỉnh sửa loại đồ kèm thêm
        } else {
            return "redirect:/loai-do-kem-them"; // Chuyển hướng về trang danh sách loại đồ kèm thêm nếu không tìm thấy loại đồ kèm thêm với id đã cho
        }
    }

    @PostMapping("/edit/{id}")
    public String editLoaiDoKemThem(@PathVariable("id") int id, @ModelAttribute("loaiDoKemThem") LoaiDoKemThem loaiDoKemThem) {
        loaiDoKemThem.setMaLoaiDoKemThem(id);
        loaiDoKemThemService.saveLoaiDoKemThem(loaiDoKemThem);
        return "redirect:/loai-do-kem-them"; // Chuyển hướng về trang danh sách loại đồ kèm thêm sau khi chỉnh sửa thành công
    }

    @GetMapping("/delete/{id}")
    public String deleteLoaiDoKemThem(@PathVariable("id") int id) {
        loaiDoKemThemService.deleteLoaiDoKemThem(id);
        return "redirect:/loai-do-kem-them"; // Chuyển hướng về trang danh sách loại đồ kèm thêm sau khi xóa thành công
    }
}