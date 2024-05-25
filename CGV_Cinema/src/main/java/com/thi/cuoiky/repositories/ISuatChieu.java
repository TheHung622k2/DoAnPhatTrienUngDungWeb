package com.thi.cuoiky.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thi.cuoiky.entities.SuatChieu;

public interface ISuatChieu extends JpaRepository<SuatChieu, Integer>{
	List<SuatChieu> findByPhim_MaPhim(Integer phimId);
    List<SuatChieu> findByPhongChieu_MaPhong(Integer phongChieuId);
}
