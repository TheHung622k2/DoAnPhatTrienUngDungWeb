package com.thi.cuoiky.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thi.cuoiky.entities.HoaDon;

public interface IHoaDon extends JpaRepository<HoaDon, Integer>{
	List<HoaDon> findByVe_MaVe(Integer veId);
}
