package com.thi.cuoiky.services;

import java.util.List;

import com.thi.cuoiky.entities.Ghe;

public interface GheService {
	List<Ghe> getAllGhe();
    Ghe getGheById(int id);
    void saveGhe(Ghe ghe);
    void deleteGhe(int id);
}
