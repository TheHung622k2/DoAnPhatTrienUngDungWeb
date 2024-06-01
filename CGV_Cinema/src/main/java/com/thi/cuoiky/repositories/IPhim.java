package com.thi.cuoiky.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.thi.cuoiky.entities.Phim;
import com.thi.cuoiky.entities.TheLoai;

public interface IPhim extends JpaRepository<Phim, Integer>{
	@Query("SELECT p.theLoai FROM Phim p WHERE p.maPhim = ?1")
    List<TheLoai> layTheLoaiCuaPhim(int maPhim);
	
	Page<Phim> findByTenPhimContainingIgnoreCase(String tenPhim, Pageable pageable);
}
