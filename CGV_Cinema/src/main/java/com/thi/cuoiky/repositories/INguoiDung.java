package com.thi.cuoiky.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thi.cuoiky.entities.NguoiDung;

public interface INguoiDung extends JpaRepository<NguoiDung, Integer> {
	NguoiDung findByTenDangNhap(String tenDangNhap);
}
