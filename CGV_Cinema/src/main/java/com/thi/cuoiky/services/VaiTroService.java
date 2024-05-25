package com.thi.cuoiky.services;

import java.util.List;

import com.thi.cuoiky.entities.VaiTro;

public interface VaiTroService {
	List<VaiTro> getAllVaiTro();
    VaiTro getVaiTroById(int id);
    void saveVaiTro(VaiTro vaiTro);
    void deleteVaiTro(int id);
}	
