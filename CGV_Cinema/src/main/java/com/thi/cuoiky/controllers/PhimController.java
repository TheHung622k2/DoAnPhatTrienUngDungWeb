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

import com.thi.cuoiky.entities.Phim;
import com.thi.cuoiky.entities.TheLoai;
import com.thi.cuoiky.services.PhimService;
import com.thi.cuoiky.services.TheLoaiService;

@Controller
@RequestMapping("/phim")
public class PhimController {

    @Autowired
    private PhimService phimService;

    @Autowired
    private TheLoaiService theLoaiService;

    @GetMapping
    public String getAllPhim(Model model) {
        List<Phim> phimList = phimService.getAllPhim();
        model.addAttribute("phimList", phimList);
        return "phim/list"; // Trả về tên của Thymeleaf template để hiển thị danh sách phim
    }

    @GetMapping("/add")
    public String showAddPhimForm(Model model) {
        Phim phim = new Phim();
        List<TheLoai> theLoaiList = theLoaiService.getAllTheLoai();
        model.addAttribute("phim", phim);
        model.addAttribute("theLoaiList", theLoaiList);
        return "phim/add"; // Trả về tên của Thymeleaf template để hiển thị form thêm phim
    }

    @PostMapping("/add")
    public String addPhim(@ModelAttribute("phim") Phim phim) {
        phimService.savePhim(phim);
        return "redirect:/phim"; // Chuyển hướng về trang danh sách phim sau khi thêm thành công
    }

    @GetMapping("/edit/{id}")
    public String showEditPhimForm(@PathVariable("id") int id, Model model) {
        Phim phim = phimService.getPhimById(id);
        List<TheLoai> theLoaiList = theLoaiService.getAllTheLoai();
        if (phim != null) {
            model.addAttribute("phim", phim);
            model.addAttribute("theLoaiList", theLoaiList);
            return "phim/edit"; // Trả về tên của Thymeleaf template để hiển thị form chỉnh sửa phim
        } else {
            return "redirect:/phim"; // Chuyển hướng về trang danh sách phim nếu không tìm thấy phim với id đã cho
        }
    }

    @PostMapping("/edit/{id}")
    public String editPhim(@PathVariable("id") int id, @ModelAttribute("phim") Phim phim) {
        phim.setMaPhim(id);
        phimService.savePhim(phim);
        return "redirect:/phim"; // Chuyển hướng về trang danh sách phim sau khi chỉnh sửa thành công
    }

    @GetMapping("/delete/{id}")
    public String deletePhim(@PathVariable("id") int id) {
        phimService.deletePhim(id);
        return "redirect:/phim"; // Chuyển hướng về trang danh sách phim sau khi xóa thành công
    }

    @GetMapping("/{id}/the-loai")
    public String getTheLoaiCuaPhim(@PathVariable("id") int id, Model model) {
        List<TheLoai> theLoaiList = phimService.layTheLoaiCuaPhim(id);
        model.addAttribute("theLoaiList", theLoaiList);
        return "phim/the-loai"; // Trả về tên của Thymeleaf template để hiển thị danh sách thể loại của phim
    }
}