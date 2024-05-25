package com.thi.cuoiky.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thi.cuoiky.entities.Ve;

public interface IVe extends JpaRepository<Ve, Integer>{
	List<Ve> findBySuatChieu_MaSuatChieu(Integer suatChieuId);
    List<Ve> findByNguoiDung_MaNguoiDung(Integer nguoiDungId);
}
