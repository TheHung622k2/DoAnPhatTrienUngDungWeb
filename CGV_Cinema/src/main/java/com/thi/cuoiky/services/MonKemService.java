package com.thi.cuoiky.services;

import java.util.List;

import com.thi.cuoiky.entities.MonKem;

public interface MonKemService {
	List<MonKem> getAllMonKem();
    MonKem getMonKemById(int id);
    void saveMonKem(MonKem monKem);
    void deleteMonKem(int id);
}
