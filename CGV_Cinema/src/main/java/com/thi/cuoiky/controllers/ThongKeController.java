package com.thi.cuoiky.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thi.cuoiky.services.HoaDonService;

@Controller
@RequestMapping("/thongke")
public class ThongKeController {

    @Autowired
    private HoaDonService hoaDonService;

    @GetMapping("/doanhthu")
    public String xemThongKeDoanhThu(@RequestParam(value = "year", required = false, defaultValue = "2024") int year,
                                     @RequestParam(value = "startMonth", required = false, defaultValue = "1") int startMonth,
                                     @RequestParam(value = "endMonth", required = false, defaultValue = "12") int endMonth,
                                     Model model) {
        List<Object[]> monthlyRevenue = hoaDonService.getMonthlyRevenueByYearAndMonthRange(year, startMonth, endMonth);
        model.addAttribute("monthlyRevenue", monthlyRevenue);
        model.addAttribute("selectedYear", year);
        model.addAttribute("years", hoaDonService.getDistinctYears());
        model.addAttribute("startMonth", startMonth);
        model.addAttribute("endMonth", endMonth);
        return "thongke/doanhthu";
    }
}
