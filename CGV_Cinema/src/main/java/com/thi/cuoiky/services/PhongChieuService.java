package com.thi.cuoiky.services;

import java.util.List;

import com.thi.cuoiky.entities.PhongChieu;

public interface PhongChieuService {
	List<PhongChieu> getAllPhongChieu();
    PhongChieu getPhongChieuById(int id);
    void savePhongChieu(PhongChieu phongChieu);
    void deletePhongChieu(int id);
}
