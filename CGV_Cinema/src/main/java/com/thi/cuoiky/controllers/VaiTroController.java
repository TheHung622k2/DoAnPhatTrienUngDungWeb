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

import com.thi.cuoiky.entities.VaiTro;
import com.thi.cuoiky.services.VaiTroService;

@Controller
@RequestMapping("/vai-tro")
public class VaiTroController {

    @Autowired
    private VaiTroService vaiTroService;

    @GetMapping
    public String getAllVaiTro(Model model) {
        List<VaiTro> vaiTroList = vaiTroService.getAllVaiTro();
        model.addAttribute("vaiTroList", vaiTroList);
        return "vai-tro/list"; // Trả về tên của Thymeleaf template để hiển thị danh sách vai trò
    }

    @GetMapping("/add")
    public String showAddVaiTroForm(Model model) {
        VaiTro vaiTro = new VaiTro();
        model.addAttribute("vaiTro", vaiTro);
        return "vai-tro/add"; // Trả về tên của Thymeleaf template để hiển thị form thêm vai trò
    }

    @PostMapping("/add")
    public String addVaiTro(@ModelAttribute("vaiTro") VaiTro vaiTro) {
        vaiTroService.saveVaiTro(vaiTro);
        return "redirect:/vai-tro"; // Chuyển hướng về trang danh sách vai trò sau khi thêm thành công
    }

    @GetMapping("/edit/{id}")
    public String showEditVaiTroForm(@PathVariable("id") int id, Model model) {
        VaiTro vaiTro = vaiTroService.getVaiTroById(id);
        if (vaiTro != null) {
            model.addAttribute("vaiTro", vaiTro);
            return "vai-tro/edit"; // Trả về tên của Thymeleaf template để hiển thị form chỉnh sửa vai trò
        } else {
            return "redirect:/vai-tro"; // Chuyển hướng về trang danh sách vai trò nếu không tìm thấy vai trò với id đã cho
        }
    }

    @PostMapping("/edit/{id}")
    public String editVaiTro(@PathVariable("id") int id, @ModelAttribute("vaiTro") VaiTro vaiTro) {
        vaiTro.setMaVaiTro(id);
        vaiTroService.saveVaiTro(vaiTro);
        return "redirect:/vai-tro"; // Chuyển hướng về trang danh sách vai trò sau khi chỉnh sửa thành công
    }

    @GetMapping("/delete/{id}")
    public String deleteVaiTro(@PathVariable("id") int id) {
        vaiTroService.deleteVaiTro(id);
        return "redirect:/vai-tro"; // Chuyển hướng về trang danh sách vai trò sau khi xóa thành công
    }
}
