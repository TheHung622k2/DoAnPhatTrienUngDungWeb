package com.thi.cuoiky.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thi.cuoiky.entities.LoaiDoKemThem;
import com.thi.cuoiky.entities.MonKem;
import com.thi.cuoiky.services.LoaiDoKemThemService;
import com.thi.cuoiky.services.MonKemService;

@Controller
@RequestMapping("/mon-kem")
public class MonKemController {

    private static final String UPLOAD_DIR = "src/main/resources/static/food_and_drink/";

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
    public String addMonKem(@ModelAttribute("monKem") MonKem monKem, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file != null && !file.isEmpty()) {
            try {
                String fileName = file.getOriginalFilename();
                Path path = Paths.get(UPLOAD_DIR + fileName);
                
                // Ghi đè tệp hiện có nếu đã tồn tại
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

                monKem.setHinhAnh("food_and_drink/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("message", "File upload failed!");
                return "redirect:/mon-kem/add";
            }
        }
        monKemService.saveMonKem(monKem);
        return "redirect:/mon-kem"; // Chuyển hướng về trang danh sách món kem sau khi thêm thành công
    }

    @GetMapping("/edit/{id}")
    public String showEditMonKemForm(@PathVariable("id") int id, Model model) {
        MonKem monKem = monKemService.getMonKemById(id);
        List<LoaiDoKemThem> loaiDoKemThemList = loaiDoKemThemService.getAllLoaiDoKemThem();
        model.addAttribute("monKem", monKem);
        model.addAttribute("loaiDoKemThemList", loaiDoKemThemList);
        return "mon-kem/test"; // Trả về trang chỉnh sửa món kèm
    }

    @PostMapping("/edit")
    public String editMonKem(@ModelAttribute("monKem") MonKem monKem, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file != null && !file.isEmpty()) {
            try {
                String fileName = file.getOriginalFilename();
                Path path = Paths.get(UPLOAD_DIR + fileName);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                monKem.setHinhAnh("food_and_drink/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("message", "File upload failed!");
                return "redirect:/mon-kem/test/" + monKem.getMaMonKem();
            }
        }
        monKemService.saveMonKem(monKem);
        return "redirect:/mon-kem"; // Chuyển hướng về trang danh sách món kèm sau khi chỉnh sửa thành công
    }


    @GetMapping("/delete/{id}")
    public String deleteMonKem(@PathVariable("id") int id) {
        monKemService.deleteMonKem(id);
        return "redirect:/mon-kem"; // Chuyển hướng về trang danh sách món kem sau khi xóa thành công
    }
}
