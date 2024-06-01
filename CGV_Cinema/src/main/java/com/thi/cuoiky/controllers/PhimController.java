package com.thi.cuoiky.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import com.thi.cuoiky.entities.Phim;
import com.thi.cuoiky.entities.TheLoai;
import com.thi.cuoiky.services.PhimService;

@Controller
@RequestMapping("/phim")
public class PhimController {
	
	private static final String UPLOAD_DIR = "src/main/resources/static/upload/";

    @Autowired
    private PhimService phimService;

    @GetMapping
    public String getAllPhim(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Phim> phimPage = phimService.getAllPhim(pageable);
        model.addAttribute("phimPage", phimPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", phimPage.getTotalPages());
        return "phim/list";
    }
    
    @GetMapping("/search")
    public String getAllPhim(@RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "10") int size,
                             @RequestParam(defaultValue = "") String search,
                             Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Phim> phimPage = phimService.getAllPhim(search, pageable);
        model.addAttribute("phimPage", phimPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", phimPage.getTotalPages());
        model.addAttribute("search", search);
        return "phim/list";
    }

    @GetMapping("/add")
    public String showAddPhimForm(Model model) {
        Phim phim = new Phim();
        model.addAttribute("phim", phim);
        return "phim/add";
    }

    @PostMapping("/add")
    public String addPhim(@ModelAttribute("phim") Phim phim, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file != null && !file.isEmpty()) {
            try {
                String fileName = file.getOriginalFilename();
                Path path = Paths.get(UPLOAD_DIR + fileName);

                // Đọc nội dung tệp vào bộ nhớ
                byte[] bytes = file.getBytes();

                // Ghi đè tệp hiện có nếu đã tồn tại
                Files.write(path, bytes, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

                phim.setAnh("/upload/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("message", "Tải tệp thất bại!");
                return "redirect:/phim/add";
            }
        }

        phimService.savePhim(phim);
        return "redirect:/phim";
    }


    @GetMapping("/edit/{id}")
    public String showEditPhimForm(@PathVariable("id") int id, Model model) {
        Phim phim = phimService.getPhimById(id);
        if (phim != null) {
            model.addAttribute("phim", phim);
            return "phim/edit";
        } else {
            return "redirect:/phim"; // Chuyển hướng về trang danh sách phim nếu không tìm thấy phim với id đã cho
        }
    }

    @PostMapping("/edit/{id}")
    public String editPhim(@PathVariable("id") int id, @ModelAttribute("phim") Phim phim, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        Phim existingPhim = phimService.getPhimById(id);
        if (existingPhim == null) {
            redirectAttributes.addFlashAttribute("message", "Phim không tồn tại!");
            return "redirect:/phim";
        }

        if (file != null && !file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            Path path = Paths.get(UPLOAD_DIR + fileName);
            try {
                // Đọc nội dung tệp vào bộ nhớ
                byte[] bytes = file.getBytes();

                // Ghi đè tệp hiện có nếu đã tồn tại
                Files.write(path, bytes, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

                phim.setAnh("/upload/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("message", "Tải tệp thất bại!");
                return "redirect:/phim/edit/" + id;
            }
        } else {
            phim.setAnh(existingPhim.getAnh());
        }

        phim.setMaPhim(id);
        phimService.savePhim(phim);
        return "redirect:/phim";
    }

    @GetMapping("/delete/{id}")
    public String deletePhim(@PathVariable("id") int id) {
        phimService.deletePhim(id);
        return "redirect:/phim";
    }

    @GetMapping("/{id}/the-loai")
    public String getTheLoaiCuaPhim(@PathVariable("id") int id, Model model) {
        List<TheLoai> theLoaiList = phimService.layTheLoaiCuaPhim(id);
        model.addAttribute("theLoaiList", theLoaiList);
        return "phim/the-loai";
    }
}