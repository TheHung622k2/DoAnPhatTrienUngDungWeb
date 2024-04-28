package com.thi.cuoiky.services;

import java.util.List;

import com.thi.cuoiky.entities.Phim;

public interface PhimService {
    List<Phim> getAllPhim();
    Phim getPhimById(int id);
    void savePhim(Phim phim);
    void deletePhim(int id);
}
