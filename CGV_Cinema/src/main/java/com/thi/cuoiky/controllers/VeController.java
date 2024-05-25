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

import com.thi.cuoiky.entities.Ghe;
import com.thi.cuoiky.entities.MonKem;
import com.thi.cuoiky.entities.NguoiDung;
import com.thi.cuoiky.entities.SuatChieu;
import com.thi.cuoiky.entities.Ve;
import com.thi.cuoiky.services.GheService;
import com.thi.cuoiky.services.MonKemService;
import com.thi.cuoiky.services.NguoiDungService;
import com.thi.cuoiky.services.SuatChieuService;
import com.thi.cuoiky.services.VeService;

@Controller
@RequestMapping("/ve")
public class VeController {

    @Autowired
    private VeService veService;

    @Autowired
    private SuatChieuService suatChieuService;

    @Autowired
    private GheService gheService;

    @Autowired
    private NguoiDungService nguoiDungService;

    @Autowired
    private MonKemService monKemService;

    @GetMapping
    public String getAllVe(Model model) {
        List<Ve> veList = veService.getAllVe();
        model.addAttribute("veList", veList);
        return "ve/list"; // Trả về tên của Thymeleaf template để hiển thị danh sách vé
    }

    @GetMapping("/add")
    public String showAddVeForm(Model model) {
        Ve ve = new Ve();
        List<SuatChieu> suatChieuList = suatChieuService.getAllSuatChieu();
        List<Ghe> gheList = gheService.getAllGhe();
        List<NguoiDung> nguoiDungList = nguoiDungService.getAllNguoiDung();
        List<MonKem> monKemList = monKemService.getAllMonKem();
        model.addAttribute("ve", ve);
        model.addAttribute("suatChieuList", suatChieuList);
        model.addAttribute("gheList", gheList);
        model.addAttribute("nguoiDungList", nguoiDungList);
        model.addAttribute("monKemList", monKemList);
        return "ve/add"; // Trả về tên của Thymeleaf template để hiển thị form thêm vé
    }

    @PostMapping("/add")
    public String addVe(@ModelAttribute("ve") Ve ve) {
        veService.saveVe(ve);
        return "redirect:/ve"; // Chuyển hướng về trang danh sách vé sau khi thêm thành công
    }

    @GetMapping("/edit/{id}")
    public String showEditVeForm(@PathVariable("id") int id, Model model) {
        Ve ve = veService.getVeById(id);
        List<SuatChieu> suatChieuList = suatChieuService.getAllSuatChieu();
        List<Ghe> gheList = gheService.getAllGhe();
        List<NguoiDung> nguoiDungList = nguoiDungService.getAllNguoiDung();
        List<MonKem> monKemList = monKemService.getAllMonKem();
        if (ve != null) {
            model.addAttribute("ve", ve);
            model.addAttribute("suatChieuList", suatChieuList);
            model.addAttribute("gheList", gheList);
            model.addAttribute("nguoiDungList", nguoiDungList);
            model.addAttribute("monKemList", monKemList);
            return "ve/edit"; // Trả về tên của Thymeleaf template để hiển thị form chỉnh sửa vé
        } else {
            return "redirect:/ve"; // Chuyển hướng về trang danh sách vé nếu không tìm thấy vé với id đã cho
        }
    }

    @PostMapping("/edit/{id}")
    public String editVe(@PathVariable("id") int id, @ModelAttribute("ve") Ve ve) {
        ve.setMaVe(id);
        veService.saveVe(ve);
        return "redirect:/ve"; // Chuyển hướng về trang danh sách vé sau khi chỉnh sửa thành công
    }

    @GetMapping("/delete/{id}")
    public String deleteVe(@PathVariable("id") int id) {
        veService.deleteVe(id);
        return "redirect:/ve"; // Chuyển hướng về trang danh sách vé sau khi xóa thành công
    }

    @GetMapping("/suat-chieu/{suatChieuId}")
    public String getVeBySuatChieuId(@PathVariable("suatChieuId") int suatChieuId, Model model) {
        List<Ve> veList = veService.getVeBySuatChieuId(suatChieuId);
        model.addAttribute("veList", veList);
        return "ve/list"; // Trả về tên của Thymeleaf template để hiển thị danh sách vé theo suất chiếu
    }

    @GetMapping("/nguoi-dung/{nguoiDungId}")
    public String getVeByNguoiDungId(@PathVariable("nguoiDungId") int nguoiDungId, Model model) {
        List<Ve> veList = veService.getVeByNguoiDungId(nguoiDungId);
        model.addAttribute("veList", veList);
        return "ve/list"; // Trả về tên của Thymeleaf template để hiển thị danh sách vé theo người dùng
    }
}