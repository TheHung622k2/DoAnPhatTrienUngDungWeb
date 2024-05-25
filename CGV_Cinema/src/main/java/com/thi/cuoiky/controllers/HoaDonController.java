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

import com.thi.cuoiky.entities.HoaDon;
import com.thi.cuoiky.services.HoaDonService;

@Controller
@RequestMapping("/hoadon")
public class HoaDonController {

    @Autowired
    private HoaDonService hoaDonService;

    @GetMapping
    public String getAllHoaDon(Model model) {
        List<HoaDon> hoaDonList = hoaDonService.getAllHoaDon();
        model.addAttribute("hoaDonList", hoaDonList);
        return "hoadon/list"; // Trả về tên của Thymeleaf template để hiển thị danh sách hóa đơn
    }

    @GetMapping("/add")
    public String showAddHoaDonForm(Model model) {
        HoaDon hoaDon = new HoaDon();
        model.addAttribute("hoaDon", hoaDon);
        return "hoadon/add"; // Trả về tên của Thymeleaf template để hiển thị form thêm hóa đơn
    }

    @PostMapping("/add")
    public String addHoaDon(@ModelAttribute("hoaDon") HoaDon hoaDon) {
        hoaDonService.saveHoaDon(hoaDon);
        return "redirect:/hoadon"; // Chuyển hướng về trang danh sách hóa đơn sau khi thêm thành công
    }

    @GetMapping("/edit/{id}")
    public String showEditHoaDonForm(@PathVariable("id") int id, Model model) {
        HoaDon hoaDon = hoaDonService.getHoaDonById(id);
        if (hoaDon != null) {
            model.addAttribute("hoaDon", hoaDon);
            return "hoadon/edit"; // Trả về tên của Thymeleaf template để hiển thị form chỉnh sửa hóa đơn
        } else {
            return "redirect:/hoadon"; // Chuyển hướng về trang danh sách hóa đơn nếu không tìm thấy hóa đơn với id đã cho
        }
    }

    @PostMapping("/edit/{id}")
    public String editHoaDon(@PathVariable("id") int id, @ModelAttribute("hoaDon") HoaDon hoaDon) {
        hoaDon.setMaHoaDon(id);
        hoaDonService.saveHoaDon(hoaDon);
        return "redirect:/hoadon"; // Chuyển hướng về trang danh sách hóa đơn sau khi chỉnh sửa thành công
    }

    @GetMapping("/delete/{id}")
    public String deleteHoaDon(@PathVariable("id") int id) {
        hoaDonService.deleteHoaDon(id);
        return "redirect:/hoadon"; // Chuyển hướng về trang danh sách hóa đơn sau khi xóa thành công
    }

    @GetMapping("/ve/{veId}")
    public String getHoaDonByVeId(@PathVariable("veId") int veId, Model model) {
        List<HoaDon> hoaDonList = hoaDonService.getHoaDonByVeId(veId);
        model.addAttribute("hoaDonList", hoaDonList);
        return "hoadon/list"; // Trả về tên của Thymeleaf template để hiển thị danh sách hóa đơn theo vé
    }
}