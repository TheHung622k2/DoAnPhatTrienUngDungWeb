package com.thi.cuoiky.controllers;

import java.util.ArrayList;
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

import com.thi.cuoiky.dtos.PhimTheLoaiDTO;
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
        List<PhimTheLoaiDTO> phimTheLoaiList = phimTheLoaiService.getAllPhimWithGroupedTheLoai();
        model.addAttribute("phimTheLoaiList", phimTheLoaiList);
        return "phim-the-loai/list";
    }

    @GetMapping("/add")
    public String showAddPhimTheLoaiForm(@RequestParam(value = "phimId", required = false) Integer phimId, Model model) {
        Phim_TheLoai phimTheLoai = new Phim_TheLoai();
        List<Phim> phimList = phimService.getAllPhim();
        List<TheLoai> theLoaiList = theLoaiService.getAllTheLoai();
        List<TheLoai> selectedTheLoaiList = new ArrayList<>();
        Phim selectedPhim = null;

        if (phimId != null) {
            List<Phim_TheLoai> phimTheLoaiList = phimTheLoaiService.getPhimTheLoaiByPhimId(phimId);
            for (Phim_TheLoai pt : phimTheLoaiList) {
                selectedTheLoaiList.add(pt.getMaTheLoai());
            }
            selectedPhim = phimService.getPhimById(phimId); // Lấy thông tin phim đã chọn
        }

        model.addAttribute("phimTheLoai", phimTheLoai);
        model.addAttribute("phimList", phimList);
        model.addAttribute("theLoaiList", theLoaiList);
        model.addAttribute("selectedTheLoaiList", selectedTheLoaiList);
        model.addAttribute("selectedPhim", selectedPhim); // Thêm phim đã chọn vào model

        return "phim-the-loai/add";
    }

    @PostMapping("/add")
    public String addPhimTheLoai(@ModelAttribute("phimTheLoai") Phim_TheLoai phimTheLoai) {
        phimTheLoaiService.savePhimTheLoai(phimTheLoai);
        return "redirect:/phim-the-loai";
    }

    @GetMapping("/delete/{phimId}")
    public String showDeletePhimTheLoaiForm(@PathVariable("phimId") int phimId, Model model) {
        List<Phim_TheLoai> phimTheLoaiList = phimTheLoaiService.getPhimTheLoaiByPhimId(phimId);
        model.addAttribute("phimId", phimId);
        model.addAttribute("phimTheLoaiList", phimTheLoaiList);
        return "phim-the-loai/delete";
    }

    @PostMapping("/delete/{phimId}")
    public String deleteSelectedPhimTheLoai(@PathVariable("phimId") int phimId, @RequestParam("theLoaiIds") List<Integer> theLoaiIds) {
        for (Integer theLoaiId : theLoaiIds) {
            PhimTheLoaiId id = new PhimTheLoaiId(phimId, theLoaiId);
            phimTheLoaiService.deletePhimTheLoai(id);
        }
        return "redirect:/phim-the-loai";
    }

}