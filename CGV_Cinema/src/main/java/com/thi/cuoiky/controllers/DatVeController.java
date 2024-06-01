package com.thi.cuoiky.controllers;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

import jakarta.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/dat-ve")
public class DatVeController {

    @Autowired
    private SuatChieuService suatChieuService;

    @Autowired
    private GheService gheService;

    @Autowired
    private MonKemService monKemService;

    @Autowired
    private VeService veService;

    @Autowired
    private NguoiDungService nguoiDungService;

    @GetMapping("/{maPhim}")
    public String hienThiTrangDatVe(@PathVariable Integer maPhim, Model model, HttpSession session) throws JsonProcessingException {
        List<SuatChieu> danhSachSuatChieu = suatChieuService.getSuatChieuByPhimId(maPhim);
        List<Ghe> danhSachGhe = gheService.getAllGhe();
        List<MonKem> danhSachMonKem = monKemService.getAllMonKem();
        Integer maNguoiDung = (Integer) session.getAttribute("maNguoiDung");

        // Định dạng thời gian chiếu
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        List<Map<String, String>> danhSachSuatChieuFormatted = danhSachSuatChieu.stream()
                .map(suatChieu -> Map.of(
                        "maSuatChieu", suatChieu.getMaSuatChieu().toString(),
                        "tenPhim", suatChieu.getPhim().getTenPhim(),
                        "tenPhong", suatChieu.getPhongChieu().getTenPhong(),
                        "thoiGianChieu", suatChieu.getThoiGianChieu().format(formatter)
                ))
                .collect(Collectors.toList());

        // Lấy tên phim từ danh sách suất chiếu
        String tenPhim = danhSachSuatChieu.get(0).getPhim().getTenPhim();

        // Lấy danh sách ghế đã được đặt
        Map<Integer, List<Integer>> gheDaDatMap = danhSachSuatChieu.stream()
                .collect(Collectors.toMap(
                        SuatChieu::getMaSuatChieu,
                        suatChieu -> veService.getVeBySuatChieuId(suatChieu.getMaSuatChieu())
                                .stream()
                                .map(ve -> ve.getGhe().getMaGhe())
                                .collect(Collectors.toList())
                ));

        ObjectMapper objectMapper = new ObjectMapper();
        String gheDaDatMapJson = objectMapper.writeValueAsString(gheDaDatMap);

        model.addAttribute("danhSachSuatChieu", danhSachSuatChieuFormatted);
        model.addAttribute("danhSachGhe", danhSachGhe);
        model.addAttribute("danhSachMonKem", danhSachMonKem);
        model.addAttribute("maNguoiDung", maNguoiDung);
        model.addAttribute("giaVeMacDinh", BigDecimal.valueOf(90000));
        model.addAttribute("gheDaDatMapJson", gheDaDatMapJson);
        model.addAttribute("tenPhim", tenPhim);

        return "dat-ve/form-dat-ve";
    }

    @PostMapping("/dat")
    public String datVe(@RequestParam("maSuatChieu") Integer maSuatChieu, 
                        @RequestParam(value = "maGhe", required = true) Integer maGhe, 
                        @RequestParam(value = "maMonKem", required = false) Integer maMonKem, 
                        @RequestParam("maNguoiDung") Integer maNguoiDung, 
                        @RequestParam("giaVe") BigDecimal giaVe, Model model) throws JsonProcessingException {

        if (maGhe == null) {
            model.addAttribute("error", "Vui lòng chọn ghế.");
            return "redirect:/dat-ve/" + maSuatChieu;  // or the appropriate path to your form
        }

        // Kiểm tra ghế đã được đặt chưa
        List<Ve> veDaDat = veService.getVeBySuatChieuId(maSuatChieu);
        boolean gheDaDat = veDaDat.stream().anyMatch(ve -> ve.getGhe().getMaGhe().equals(maGhe));
        if (gheDaDat) {
            List<SuatChieu> danhSachSuatChieu = suatChieuService.getSuatChieuByPhimId(maSuatChieu);
            List<Ghe> danhSachGhe = gheService.getAllGhe();
            List<MonKem> danhSachMonKem = monKemService.getAllMonKem();
            
            // Lấy danh sách ghế đã được đặt
            Map<Integer, List<Integer>> gheDaDatMap = new HashMap<>();
            for (SuatChieu suatChieu : danhSachSuatChieu) {
                veDaDat = veService.getVeBySuatChieuId(suatChieu.getMaSuatChieu());
                List<Integer> gheDaDatList = veDaDat.stream().map(ve -> ve.getGhe().getMaGhe()).collect(Collectors.toList());
                gheDaDatMap.put(suatChieu.getMaSuatChieu(), gheDaDatList);
            }

            ObjectMapper objectMapper = new ObjectMapper();
            String gheDaDatMapJson = objectMapper.writeValueAsString(gheDaDatMap);

            model.addAttribute("danhSachSuatChieu", danhSachSuatChieu);
            model.addAttribute("danhSachGhe", danhSachGhe);
            model.addAttribute("danhSachMonKem", danhSachMonKem);
            model.addAttribute("maNguoiDung", maNguoiDung);
            model.addAttribute("giaVeMacDinh", BigDecimal.valueOf(90000));
            model.addAttribute("gheDaDatMapJson", gheDaDatMapJson);

            model.addAttribute("error", "Ghế này đã được đặt. Vui lòng chọn ghế khác.");
            return "dat-ve/form-dat-ve";
        }

        SuatChieu suatChieu = suatChieuService.getSuatChieuById(maSuatChieu);
        Ghe ghe = gheService.getGheById(maGhe);
        MonKem monKem = null;
        if (maMonKem != null && maMonKem != 0) {
            monKem = monKemService.getMonKemById(maMonKem);
        }
        NguoiDung nguoiDung = nguoiDungService.getNguoiDungById(maNguoiDung);

        Ve ve = new Ve();
        ve.setSuatChieu(suatChieu);
        ve.setGhe(ghe);
        ve.setMonKem(monKem);
        ve.setNguoiDung(nguoiDung);
        ve.setGiaVe(giaVe);

        // Thêm mã ngẫu nhiên cho randomString
        String randomString = generateRandomString(8);
        ve.setRandomString(randomString);

        veService.saveVe(ve);

        model.addAttribute("message", "Đặt vé thành công!");
        return "redirect:/dat-ve-tc";
    }

    private String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            randomString.append(characters.charAt(random.nextInt(characters.length())));
        }
        return randomString.toString();
    }
}
