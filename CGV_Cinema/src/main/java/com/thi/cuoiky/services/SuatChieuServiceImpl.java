package com.thi.cuoiky.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    	return suatChieuRepository.findByPhongChieu_MaPhongOrderByThoiGianChieuAsc(phongChieuId);
    }
    
    @Override
    public List<SuatChieu> getSuatChieuByThoiGianChieu(LocalDateTime startTime, LocalDateTime endTime) {
        return suatChieuRepository.findByThoiGianChieuBetweenOrderByThoiGianChieuAsc(startTime, endTime);
    }
    
    @Override
    public List<SuatChieu> getSuatChieuByPhimAndPhongChieu(int phimId, int phongChieuId) {
        return suatChieuRepository.findByPhim_MaPhimAndPhongChieu_MaPhongOrderByThoiGianChieuAsc(phimId, phongChieuId);
    }
    
    @Override
    public Page<SuatChieu> getSuatChieuPage(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return suatChieuRepository.findAllByOrderByThoiGianChieuAsc(pageable);
    }
    
    @Override
    public Page<SuatChieu> getSuatChieuByThoiGianChieu(LocalDateTime startTime, LocalDateTime endTime, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return suatChieuRepository.findByThoiGianChieuBetweenOrderByThoiGianChieuAsc(startTime, endTime, pageable);
    }
}
