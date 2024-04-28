package com.thi.cuoiky.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thi.cuoiky.entities.PhimTheLoaiId;
import com.thi.cuoiky.entities.Phim_TheLoai;

public interface IPhimTheLoai extends JpaRepository<Phim_TheLoai, PhimTheLoaiId>{

}
