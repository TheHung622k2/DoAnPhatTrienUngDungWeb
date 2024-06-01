package com.thi.cuoiky.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thi.cuoiky.entities.Ve;
import com.thi.cuoiky.services.VeService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/dat-ve-tc")
public class DatVeTCController {

    @Autowired
    private VeService veService;

    @GetMapping
    public String hienThiVe(HttpSession session, Model model) {
        Integer maNguoiDung = (Integer) session.getAttribute("maNguoiDung");
        List<Ve> ve = veService.getVeByNguoiDungId(maNguoiDung);

        model.addAttribute("ve", ve);       
        return "dat-ve/form-dat-ve-thanh-cong";
    }
}
