package com.thi.cuoiky.services;

import java.util.List;

import com.thi.cuoiky.entities.SuatChieu;

public interface SuatChieuService {
	List<SuatChieu> getAllSuatChieu();
    SuatChieu getSuatChieuById(int id);
    void saveSuatChieu(SuatChieu suatChieu);
    void deleteSuatChieu(int id);
    List<SuatChieu> getSuatChieuByPhimId(int phimId);
    List<SuatChieu> getSuatChieuByPhongChieuId(int phongChieuId);
}
