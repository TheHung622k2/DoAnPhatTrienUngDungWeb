package com.thi.cuoiky.services;

import java.util.List;

import com.thi.cuoiky.entities.TheLoai;

public interface TheLoaiService {
	List<TheLoai> getAllTheLoai();
    TheLoai getTheLoaiById(int id);
    void saveTheLoai(TheLoai theLoai);
    void deleteTheLoai(int id);
}
