package com.thi.cuoiky.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thi.cuoiky.entities.NguoiDung;
import com.thi.cuoiky.entities.VaiTro;
import com.thi.cuoiky.services.NguoiDungService;
import com.thi.cuoiky.services.VaiTroService;

@Controller
@RequestMapping("/nguoi-dung")
public class NguoiDungController {

    @Autowired
    private NguoiDungService nguoiDungService;

    @Autowired
    private VaiTroService vaiTroService;

    @GetMapping
    public String getAllNguoiDung(Model model) {
        List<NguoiDung> nguoiDungList = nguoiDungService.getAllNguoiDung();
        model.addAttribute("nguoiDungList", nguoiDungList);
        return "nguoi-dung/list";
    }

    @GetMapping("/add")
    public String showAddNguoiDungForm(Model model) {
        NguoiDung nguoiDung = new NguoiDung();
        List<VaiTro> vaiTroList = vaiTroService.getAllVaiTro();
        model.addAttribute("nguoiDung", nguoiDung);
        model.addAttribute("vaiTroList", vaiTroList);
        return "nguoi-dung/add"; //
    }

    @PostMapping("/add")
    public String addNguoiDung(@ModelAttribute("nguoiDung") NguoiDung nguoiDung) {
        nguoiDungService.saveNguoiDung(nguoiDung);
        return "redirect:/nguoi-dung";
    }

    @GetMapping("/edit/{id}")
    public String showEditNguoiDungForm(@PathVariable("id") int id, Model model) {
        NguoiDung nguoiDung = nguoiDungService.getNguoiDungById(id);
        List<VaiTro> vaiTroList = vaiTroService.getAllVaiTro();
        if (nguoiDung != null) {
            model.addAttribute("nguoiDung", nguoiDung);
            model.addAttribute("vaiTroList", vaiTroList);
            return "nguoi-dung/edit";
        } else {
            return "redirect:/nguoi-dung";
        }
    }

    @PostMapping("/edit/{id}")
    public String editNguoiDung(@PathVariable("id") int id, @ModelAttribute("nguoiDung") NguoiDung nguoiDung) {
        nguoiDung.setMaNguoiDung(id);
        nguoiDungService.saveNguoiDung(nguoiDung);
        return "redirect:/nguoi-dung";
    }

    @GetMapping("/delete/{id}")
    public String deleteNguoiDung(@PathVariable("id") int id) {
        nguoiDungService.deleteNguoiDung(id);
        return "redirect:/nguoi-dung";
    }

    @GetMapping("/search")
    public String searchNguoiDung(@RequestParam("tenDangNhap") String tenDangNhap, Model model) {
        NguoiDung nguoiDung = nguoiDungService.getNguoiDungByTenDangNhap(tenDangNhap);
        model.addAttribute("nguoiDung", nguoiDung);
        return "nguoi-dung/search-result";
    }
    
    @PostMapping("/toggle/{id}")
    @ResponseBody
    public ResponseEntity<Void> toggleEnabledStatus(@PathVariable("id") int id, @RequestBody Map<String, Boolean> payload) {
        NguoiDung nguoiDung = nguoiDungService.getNguoiDungById(id);
        if (nguoiDung != null && !"admin".equals(nguoiDung.getTenDangNhap())) {
            Boolean enabled = payload.get("enabled");
            if (enabled != null) {
                nguoiDungService.updateEnabledStatus(id, enabled);
            }
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
