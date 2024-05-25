package com.thi.cuoiky.services;

import java.util.List;

import com.thi.cuoiky.entities.LoaiDoKemThem;

public interface LoaiDoKemThemService {
	List<LoaiDoKemThem> getAllLoaiDoKemThem();
    LoaiDoKemThem getLoaiDoKemThemById(int id);
    void saveLoaiDoKemThem(LoaiDoKemThem loaiDoKemThem);
    void deleteLoaiDoKemThem(int id);
}
