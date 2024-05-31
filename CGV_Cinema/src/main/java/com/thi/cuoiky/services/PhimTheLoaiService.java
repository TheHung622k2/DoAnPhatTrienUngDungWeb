package com.thi.cuoiky.services;

import java.util.List;

import com.thi.cuoiky.dtos.PhimTheLoaiDTO;
import com.thi.cuoiky.entities.PhimTheLoaiId;
import com.thi.cuoiky.entities.Phim_TheLoai;

public interface PhimTheLoaiService {
	List<Phim_TheLoai> getAllPhimTheLoai();
    Phim_TheLoai getPhimTheLoaiById(PhimTheLoaiId id);
    void savePhimTheLoai(Phim_TheLoai phimTheLoai);
    void deletePhimTheLoai(PhimTheLoaiId id);
    List<Phim_TheLoai> getPhimTheLoaiByPhimId(int phimId);
    List<Phim_TheLoai> getPhimTheLoaiByTheLoaiId(int theLoaiId);   
    public List<PhimTheLoaiDTO> getAllPhimWithGroupedTheLoai();
}
