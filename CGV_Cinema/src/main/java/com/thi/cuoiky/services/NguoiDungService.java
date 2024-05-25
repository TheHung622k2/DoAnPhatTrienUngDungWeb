package com.thi.cuoiky.services;

import java.util.List;

import com.thi.cuoiky.entities.NguoiDung;

public interface NguoiDungService {
	List<NguoiDung> getAllNguoiDung();
    NguoiDung getNguoiDungById(int id);
    void saveNguoiDung(NguoiDung nguoiDung);
    void deleteNguoiDung(int id);
    NguoiDung getNguoiDungByTenDangNhap(String tenDangNhap);
}
