package com.thi.cuoiky.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thi.cuoiky.entities.SuatChieu;
import com.thi.cuoiky.repositories.ISuatChieu;

@Service
public class SuatChieuServiceImpl implements SuatChieuService {

    @Autowired
    private ISuatChieu suatChieuRepository;

    @Override
    public List<SuatChieu> getAllSuatChieu() {
        return suatChieuRepository.findAll();
    }

    @Override
    public SuatChieu getSuatChieuById(int id) {
        return suatChieuRepository.findById(id).orElse(null);
    }

    @Override
    public void saveSuatChieu(SuatChieu suatChieu) {
        suatChieuRepository.save(suatChieu);
    }

    @Override
    public void deleteSuatChieu(int id) {
        suatChieuRepository.deleteById(id);
    }

    @Override
    public List<SuatChieu> getSuatChieuByPhimId(int phimId) {
        return suatChieuRepository.findByPhim_MaPhim(phimId);
    }

    @Override
    public List<SuatChieu> getSuatChieuByPhongChieuId(int phongChieuId) {
        return suatChieuRepository.findByPhongChieu_MaPhong(phongChieuId);
    }
}
