package com.thi.cuoiky.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.thi.cuoiky.entities.SuatChieu;

public interface ISuatChieu extends JpaRepository<SuatChieu, Integer>, PagingAndSortingRepository<SuatChieu, Integer>{
	List<SuatChieu> findByPhim_MaPhim(Integer phimId);
    List<SuatChieu> findByPhongChieu_MaPhong(Integer phongChieuId);
    List<SuatChieu> findByPhongChieu_MaPhongOrderByThoiGianChieuAsc(Integer phongChieuId);
    List<SuatChieu> findByThoiGianChieuBetweenOrderByThoiGianChieuAsc(LocalDateTime startTime, LocalDateTime endTime);
    List<SuatChieu> findByPhim_MaPhimAndPhongChieu_MaPhongOrderByThoiGianChieuAsc(Integer phimId, Integer phongChieuId);
    Page<SuatChieu> findAllByOrderByThoiGianChieuAsc(Pageable pageable);
    Page<SuatChieu> findByThoiGianChieuBetweenOrderByThoiGianChieuAsc(LocalDateTime startTime, LocalDateTime endTime, Pageable pageable);
}
