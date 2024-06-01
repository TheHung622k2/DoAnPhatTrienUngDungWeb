package com.thi.cuoiky.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thi.cuoiky.entities.HoaDon;

public interface IHoaDon extends JpaRepository<HoaDon, Integer>{
	List<HoaDon> findByVe_MaVe(Integer veId);
	
	@Query("SELECT MONTH(hd.ngayTao), SUM(hd.tongTien) FROM HoaDon hd WHERE YEAR(hd.ngayTao) = :year AND MONTH(hd.ngayTao) BETWEEN :startMonth AND :endMonth GROUP BY MONTH(hd.ngayTao)")
    List<Object[]> findMonthlyRevenueByYearAndMonthRange(@Param("year") int year, @Param("startMonth") int startMonth, @Param("endMonth") int endMonth);

    @Query("SELECT DISTINCT YEAR(hd.ngayTao) FROM HoaDon hd ORDER BY YEAR(hd.ngayTao) DESC")
    List<Integer> findDistinctYears();
}
