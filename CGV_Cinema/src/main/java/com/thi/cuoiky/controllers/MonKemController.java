package com.thi.cuoiky.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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
        return "mon-kem/list";
    }

    @GetMapping("/add")
    public String showAddMonKemForm(Model model) {
        MonKem monKem = new MonKem();
        List<LoaiDoKemThem> loaiDoKemThemList = loaiDoKemThemService.getAllLoaiDoKemThem();
        model.addAttribute("monKem", monKem);
        model.addAttribute("loaiDoKemThemList", loaiDoKemThemList);
        return "mon-kem/add";
    }

    @PostMapping("/add")
    public String addMonKem(@ModelAttribute("monKem") MonKem monKem, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file != null && !file.isEmpty()) {
            try {
                String fileName = file.getOriginalFilename();
                Path path = Paths.get(UPLOAD_DIR + fileName);

                // Đọc nội dung tệp vào bộ nhớ
                byte[] bytes = file.getBytes();

                // Ghi đè tệp hiện có nếu đã tồn tại
                Files.write(path, bytes, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

                monKem.setHinhAnh("food_and_drink/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("message", "Tải tệp thất bại!");
                return "redirect:/mon-kem/add";
            }
        }

        monKemService.saveMonKem(monKem);
        return "redirect:/mon-kem";
    }

    @GetMapping("/edit/{id}")
    public String showEditMonKemForm(@PathVariable("id") int id, Model model) {
        MonKem monKem = monKemService.getMonKemById(id);
        List<LoaiDoKemThem> loaiDoKemThemList = loaiDoKemThemService.getAllLoaiDoKemThem();
        model.addAttribute("monKem", monKem);
        model.addAttribute("loaiDoKemThemList", loaiDoKemThemList);
        return "mon-kem/edit";
    }

    @PostMapping("/edit/{id}")
    public String editMonKem(@PathVariable("id") int id, @ModelAttribute("monKem") MonKem monKem, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        MonKem existingMonKem = monKemService.getMonKemById(id);
        if (existingMonKem == null) {
            redirectAttributes.addFlashAttribute("message", "Món kèm không tồn tại!");
            return "redirect:/mon-kem";
        }

        if (file != null && !file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            Path path = Paths.get(UPLOAD_DIR + fileName);
            try {
                // Đọc nội dung tệp vào bộ nhớ
                byte[] bytes = file.getBytes();

                // Ghi đè tệp hiện có nếu đã tồn tại
                Files.write(path, bytes, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

                monKem.setHinhAnh("food_and_drink/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("message", "Tải tệp thất bại!");
                return "redirect:/mon-kem/edit/" + id;
            }
        } else {
            monKem.setHinhAnh(existingMonKem.getHinhAnh());
        }

        monKem.setMaMonKem(id);
        monKemService.saveMonKem(monKem);
        return "redirect:/mon-kem";
    }

    @GetMapping("/delete/{id}")
    public String deleteMonKem(@PathVariable("id") int id) {
        monKemService.deleteMonKem(id);
        return "redirect:/mon-kem";
    }
}
