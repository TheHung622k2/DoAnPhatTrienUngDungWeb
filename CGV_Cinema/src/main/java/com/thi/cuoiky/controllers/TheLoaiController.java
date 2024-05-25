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

import com.thi.cuoiky.entities.TheLoai;
import com.thi.cuoiky.services.TheLoaiService;

@Controller
@RequestMapping("/the-loai")
public class TheLoaiController {

    @Autowired
    private TheLoaiService theLoaiService;

    @GetMapping
    public String getAllTheLoai(Model model) {
        List<TheLoai> theLoaiList = theLoaiService.getAllTheLoai();
        model.addAttribute("theLoaiList", theLoaiList);
        return "the-loai/list"; // Trả về tên của Thymeleaf template để hiển thị danh sách thể loại
    }

    @GetMapping("/add")
    public String showAddTheLoaiForm(Model model) {
        TheLoai theLoai = new TheLoai();
        model.addAttribute("theLoai", theLoai);
        return "the-loai/add"; // Trả về tên của Thymeleaf template để hiển thị form thêm thể loại
    }

    @PostMapping("/add")
    public String addTheLoai(@ModelAttribute("theLoai") TheLoai theLoai) {
        theLoaiService.saveTheLoai(theLoai);
        return "redirect:/the-loai"; // Chuyển hướng về trang danh sách thể loại sau khi thêm thành công
    }

    @GetMapping("/edit/{id}")
    public String showEditTheLoaiForm(@PathVariable("id") int id, Model model) {
        TheLoai theLoai = theLoaiService.getTheLoaiById(id);
        if (theLoai != null) {
            model.addAttribute("theLoai", theLoai);
            return "the-loai/edit"; // Trả về tên của Thymeleaf template để hiển thị form chỉnh sửa thể loại
        } else {
            return "redirect:/the-loai"; // Chuyển hướng về trang danh sách thể loại nếu không tìm thấy thể loại với id đã cho
        }
    }

    @PostMapping("/edit/{id}")
    public String editTheLoai(@PathVariable("id") int id, @ModelAttribute("theLoai") TheLoai theLoai) {
        theLoai.setMaTheLoai(id);
        theLoaiService.saveTheLoai(theLoai);
        return "redirect:/the-loai"; // Chuyển hướng về trang danh sách thể loại sau khi chỉnh sửa thành công
    }

    @GetMapping("/delete/{id}")
    public String deleteTheLoai(@PathVariable("id") int id) {
        theLoaiService.deleteTheLoai(id);
        return "redirect:/the-loai"; // Chuyển hướng về trang danh sách thể loại sau khi xóa thành công
    }
}