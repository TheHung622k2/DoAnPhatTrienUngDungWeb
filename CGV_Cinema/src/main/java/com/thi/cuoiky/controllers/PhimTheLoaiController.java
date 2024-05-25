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
import com.thi.cuoiky.entities.PhimTheLoaiId;
import com.thi.cuoiky.entities.Phim_TheLoai;
import com.thi.cuoiky.entities.TheLoai;
import com.thi.cuoiky.services.PhimService;
import com.thi.cuoiky.services.PhimTheLoaiService;
import com.thi.cuoiky.services.TheLoaiService;

@Controller
@RequestMapping("/phim-the-loai")
public class PhimTheLoaiController {

    @Autowired
    private PhimTheLoaiService phimTheLoaiService;

    @Autowired
    private PhimService phimService;

    @Autowired
    private TheLoaiService theLoaiService;

    @GetMapping
    public String getAllPhimTheLoai(Model model) {
        List<Phim_TheLoai> phimTheLoaiList = phimTheLoaiService.getAllPhimTheLoai();
        model.addAttribute("phimTheLoaiList", phimTheLoaiList);
        return "phim-the-loai/list"; // Trả về tên của Thymeleaf template để hiển thị danh sách phim-thể loại
    }

    @GetMapping("/add")
    public String showAddPhimTheLoaiForm(Model model) {
        Phim_TheLoai phimTheLoai = new Phim_TheLoai();
        List<Phim> phimList = phimService.getAllPhim();
        List<TheLoai> theLoaiList = theLoaiService.getAllTheLoai();
        model.addAttribute("phimTheLoai", phimTheLoai);
        model.addAttribute("phimList", phimList);
        model.addAttribute("theLoaiList", theLoaiList);
        return "phim-the-loai/add"; // Trả về tên của Thymeleaf template để hiển thị form thêm phim-thể loại
    }

    @PostMapping("/add")
    public String addPhimTheLoai(@ModelAttribute("phimTheLoai") Phim_TheLoai phimTheLoai) {
        phimTheLoaiService.savePhimTheLoai(phimTheLoai);
        return "redirect:/phim-the-loai"; // Chuyển hướng về trang danh sách phim-thể loại sau khi thêm thành công
    }

    @GetMapping("/edit/{phimId}/{theLoaiId}")
    public String showEditPhimTheLoaiForm(@PathVariable("phimId") int phimId, @PathVariable("theLoaiId") int theLoaiId, Model model) {
        PhimTheLoaiId id = new PhimTheLoaiId(phimId, theLoaiId);
        Phim_TheLoai phimTheLoai = phimTheLoaiService.getPhimTheLoaiById(id);
        List<Phim> phimList = phimService.getAllPhim();
        List<TheLoai> theLoaiList = theLoaiService.getAllTheLoai();
        if (phimTheLoai != null) {
            model.addAttribute("phimTheLoai", phimTheLoai);
            model.addAttribute("phimList", phimList);
            model.addAttribute("theLoaiList", theLoaiList);
            return "phim-the-loai/edit"; // Trả về tên của Thymeleaf template để hiển thị form chỉnh sửa phim-thể loại
        } else {
            return "redirect:/phim-the-loai"; // Chuyển hướng về trang danh sách phim-thể loại nếu không tìm thấy phim-thể loại với id đã cho
        }
    }

    @PostMapping("/edit/{phimId}/{theLoaiId}")
    public String editPhimTheLoai(@PathVariable("phimId") int phimId, @PathVariable("theLoaiId") int theLoaiId, @ModelAttribute("phimTheLoai") Phim_TheLoai phimTheLoai) {
        PhimTheLoaiId id = new PhimTheLoaiId(phimId, theLoaiId);
        phimTheLoai.setMaPhim(phimTheLoaiService.getPhimTheLoaiById(id).getMaPhim());
        phimTheLoai.setMaTheLoai(phimTheLoaiService.getPhimTheLoaiById(id).getMaTheLoai());
        phimTheLoaiService.savePhimTheLoai(phimTheLoai);
        return "redirect:/phim-the-loai"; // Chuyển hướng về trang danh sách phim-thể loại sau khi chỉnh sửa thành công
    }

    @GetMapping("/delete/{phimId}/{theLoaiId}")
    public String deletePhimTheLoai(@PathVariable("phimId") int phimId, @PathVariable("theLoaiId") int theLoaiId) {
        PhimTheLoaiId id = new PhimTheLoaiId(phimId, theLoaiId);
        phimTheLoaiService.deletePhimTheLoai(id);
        return "redirect:/phim-the-loai"; // Chuyển hướng về trang danh sách phim-thể loại sau khi xóa thành công
    }
}