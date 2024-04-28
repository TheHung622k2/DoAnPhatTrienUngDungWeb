package com.thi.cuoiky.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thi.cuoiky.entities.Phim;
import com.thi.cuoiky.services.PhimService;

@Controller
@RequestMapping("/phim")
public class PhimController {

	@Autowired
	private  PhimService phimService;

    @GetMapping("/list")
    public String listPhim(Model model) {
        model.addAttribute("phimList", phimService.getAllPhim());
        return "list";
    }

    @GetMapping("/detail/{id}")
    public String phimDetail(@PathVariable int id, Model model) {
        Phim phim = phimService.getPhimById(id);
        model.addAttribute("phim", phim);
        return "phim/detail";
    }

    @GetMapping("/add")
    public String addPhimForm(Model model) {
        model.addAttribute("phim", new Phim());
        return "phim/add";
    }

    @PostMapping("/add")
    public String addPhim(Phim phim) {
        phimService.savePhim(phim);
        return "redirect:/phim/list";
    }

    @GetMapping("/delete/{id}")
    public String deletePhim(@PathVariable int id) {
        phimService.deletePhim(id);
        return "redirect:/phim/list";
    }
}
