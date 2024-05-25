package com.thi.cuoiky.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thi.cuoiky.entities.PhimTheLoaiId;
import com.thi.cuoiky.entities.Phim_TheLoai;

public interface IPhimTheLoai extends JpaRepository<Phim_TheLoai, PhimTheLoaiId>{
	List<Phim_TheLoai> findByMaPhim_MaPhim(Integer phimId);
    List<Phim_TheLoai> findByMaTheLoai_MaTheLoai(Integer theLoaiId);
}
