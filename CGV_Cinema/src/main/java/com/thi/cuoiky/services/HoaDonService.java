package com.thi.cuoiky.services;

import java.util.List;

import com.thi.cuoiky.entities.HoaDon;

public interface HoaDonService {
	List<HoaDon> getAllHoaDon();
    HoaDon getHoaDonById(int id);
    void saveHoaDon(HoaDon hoaDon);
    void deleteHoaDon(int id);
    List<HoaDon> getHoaDonByVeId(int veId);
    List<Object[]> getMonthlyRevenueByYearAndMonthRange(int year, int startMonth, int endMonth);
    List<Integer> getDistinctYears();
}
