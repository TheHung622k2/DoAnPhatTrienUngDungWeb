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

import com.thi.cuoiky.entities.PhongChieu;
import com.thi.cuoiky.services.PhongChieuService;

@Controller
@RequestMapping("/phong-chieu")
public class PhongChieuController {

    @Autowired
    private PhongChieuService phongChieuService;

    @GetMapping
    public String getAllPhongChieu(Model model) {
        List<PhongChieu> phongChieuList = phongChieuService.getAllPhongChieu();
        model.addAttribute("phongChieuList", phongChieuList);
        return "phong-chieu/list"; // Trả về tên của Thymeleaf template để hiển thị danh sách phòng chiếu
    }

    @GetMapping("/add")
    public String showAddPhongChieuForm(Model model) {
        PhongChieu phongChieu = new PhongChieu();
        model.addAttribute("phongChieu", phongChieu);
        return "phong-chieu/add"; // Trả về tên của Thymeleaf template để hiển thị form thêm phòng chiếu
    }

    @PostMapping("/add")
    public String addPhongChieu(@ModelAttribute("phongChieu") PhongChieu phongChieu) {
        phongChieuService.savePhongChieu(phongChieu);
        return "redirect:/phong-chieu"; // Chuyển hướng về trang danh sách phòng chiếu sau khi thêm thành công
    }

    @GetMapping("/edit/{id}")
    public String showEditPhongChieuForm(@PathVariable("id") int id, Model model) {
        PhongChieu phongChieu = phongChieuService.getPhongChieuById(id);
        if (phongChieu != null) {
            model.addAttribute("phongChieu", phongChieu);
            return "phong-chieu/edit"; // Trả về tên của Thymeleaf template để hiển thị form chỉnh sửa phòng chiếu
        } else {
            return "redirect:/phong-chieu"; // Chuyển hướng về trang danh sách phòng chiếu nếu không tìm thấy phòng chiếu với id đã cho
        }
    }

    @PostMapping("/edit/{id}")
    public String editPhongChieu(@PathVariable("id") int id, @ModelAttribute("phongChieu") PhongChieu phongChieu) {
        phongChieu.setMaPhong(id);
        phongChieuService.savePhongChieu(phongChieu);
        return "redirect:/phong-chieu"; // Chuyển hướng về trang danh sách phòng chiếu sau khi chỉnh sửa thành công
    }

    @GetMapping("/delete/{id}")
    public String deletePhongChieu(@PathVariable("id") int id) {
        phongChieuService.deletePhongChieu(id);
        return "redirect:/phong-chieu"; // Chuyển hướng về trang danh sách phòng chiếu sau khi xóa thành công
    }
}