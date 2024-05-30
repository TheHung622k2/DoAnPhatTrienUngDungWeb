package com.thi.cuoiky.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;

import com.thi.cuoiky.entities.SuatChieu;

public interface SuatChieuService {
	List<SuatChieu> getAllSuatChieu();
    SuatChieu getSuatChieuById(int id);
    void saveSuatChieu(SuatChieu suatChieu);
    void deleteSuatChieu(int id);
    List<SuatChieu> getSuatChieuByPhimId(int phimId);
    List<SuatChieu> getSuatChieuByPhongChieuId(int phongChieuId);
    List<SuatChieu> getSuatChieuByThoiGianChieu(LocalDateTime startTime, LocalDateTime endTime);
    Page<SuatChieu> getSuatChieuPage(int pageNumber, int pageSize);
}
