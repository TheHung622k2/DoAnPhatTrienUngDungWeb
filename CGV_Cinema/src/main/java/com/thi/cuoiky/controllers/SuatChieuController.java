package com.thi.cuoiky.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thi.cuoiky.entities.Phim;
import com.thi.cuoiky.entities.PhongChieu;
import com.thi.cuoiky.entities.SuatChieu;
import com.thi.cuoiky.services.PhimService;
import com.thi.cuoiky.services.PhongChieuService;
import com.thi.cuoiky.services.SuatChieuService;

@Controller
@RequestMapping("/suat-chieu")
public class SuatChieuController {

    @Autowired
    private SuatChieuService suatChieuService;

    @Autowired
    private PhimService phimService;

    @Autowired
    private PhongChieuService phongChieuService;

    @GetMapping
    public String getAllSuatChieu(Model model, 
                                  @RequestParam(defaultValue = "0") int page, 
                                  @RequestParam(required = false) Integer phimId, 
                                  @RequestParam(required = false) Integer phongChieuId, 
                                  @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime, 
                                  @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        
        List<SuatChieu> suatChieuList;
        int totalPages = 0; 
        
        if (phimId != null && phimId != 0) {
            suatChieuList = suatChieuService.getSuatChieuByPhimId(phimId);
            totalPages = 1; 
        } else if (phongChieuId != null && phongChieuId != 0) {
            suatChieuList = suatChieuService.getSuatChieuByPhongChieuId(phongChieuId);
            totalPages = 1; 
        } else if (startTime != null && endTime != null) {
            suatChieuList = suatChieuService.getSuatChieuByThoiGianChieu(startTime, endTime);
            totalPages = 1;
        } else {
            Page<SuatChieu> suatChieuPage = suatChieuService.getSuatChieuPage(page, 10);
            suatChieuList = suatChieuPage.getContent();
            totalPages = suatChieuPage.getTotalPages();
        }
        
        model.addAttribute("currentPage", page);
        model.addAttribute("suatChieuList", suatChieuList);
        model.addAttribute("phimList", phimService.getAllPhim());
        model.addAttribute("phongChieuList", phongChieuService.getAllPhongChieu());
        model.addAttribute("totalPages", totalPages);
        return "suat-chieu/list";
    }

    @GetMapping("/add")
    public String showAddSuatChieuForm(Model model) {
        SuatChieu suatChieu = new SuatChieu();
        List<Phim> phimList = phimService.getAllPhim();
        List<PhongChieu> phongChieuList = phongChieuService.getAllPhongChieu();
        model.addAttribute("suatChieu", suatChieu);
        model.addAttribute("phimList", phimList);
        model.addAttribute("phongChieuList", phongChieuList);
        return "suat-chieu/add"; // Trả về tên của Thymeleaf template để hiển thị form thêm suất chiếu
    }

    @PostMapping("/add")
    public String addSuatChieu(@ModelAttribute("suatChieu") SuatChieu suatChieu) {
        suatChieuService.saveSuatChieu(suatChieu);
        return "redirect:/suat-chieu"; // Chuyển hướng về trang danh sách suất chiếu sau khi thêm thành công
    }

    @GetMapping("/edit/{id}")
    public String showEditSuatChieuForm(@PathVariable("id") int id, Model model) {
        SuatChieu suatChieu = suatChieuService.getSuatChieuById(id);
        List<Phim> phimList = phimService.getAllPhim();
        List<PhongChieu> phongChieuList = phongChieuService.getAllPhongChieu();
        if (suatChieu != null) {
            model.addAttribute("suatChieu", suatChieu);
            model.addAttribute("phimList", phimList);
            model.addAttribute("phongChieuList", phongChieuList);
            return "suat-chieu/edit"; // Trả về tên của Thymeleaf template để hiển thị form chỉnh sửa suất chiếu
        } else {
            return "redirect:/suat-chieu"; // Chuyển hướng về trang danh sách suất chiếu nếu không tìm thấy suất chiếu với id đã cho
        }
    }

    @PostMapping("/edit/{id}")
    public String editSuatChieu(@PathVariable("id") int id, @ModelAttribute("suatChieu") SuatChieu suatChieu) {
        suatChieu.setMaSuatChieu(id);
        suatChieuService.saveSuatChieu(suatChieu);
        return "redirect:/suat-chieu"; // Chuyển hướng về trang danh sách suất chiếu sau khi chỉnh sửa thành công
    }

    @GetMapping("/delete/{id}")
    public String deleteSuatChieu(@PathVariable("id") int id) {
        suatChieuService.deleteSuatChieu(id);
        return "redirect:/suat-chieu"; // Chuyển hướng về trang danh sách suất chiếu sau khi xóa thành công
    }
}