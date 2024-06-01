package com.thi.cuoiky.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thi.cuoiky.entities.Phim;
import com.thi.cuoiky.entities.TheLoai;

public interface PhimService {
    List<Phim> getAllPhim();
    Page<Phim> getAllPhim(Pageable pageable);
    Page<Phim> getAllPhim(String search, Pageable pageable);
    Phim getPhimById(int id);
    void savePhim(Phim phim);
    void deletePhim(int id);
    List<TheLoai> layTheLoaiCuaPhim(int maPhim);
}
