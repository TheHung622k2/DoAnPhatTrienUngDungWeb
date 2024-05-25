package com.thi.cuoiky.services;

import java.util.List;

import com.thi.cuoiky.entities.Ve;

public interface VeService {
	List<Ve> getAllVe();
    Ve getVeById(int id);
    void saveVe(Ve ve);
    void deleteVe(int id);
    List<Ve> getVeBySuatChieuId(int suatChieuId);
    List<Ve> getVeByNguoiDungId(int nguoiDungId);
}
