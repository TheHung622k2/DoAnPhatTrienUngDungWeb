package com.thi.cuoiky.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thi.cuoiky.entities.NguoiDung;
import com.thi.cuoiky.repositories.INguoiDung;

@Service
public class NguoiDungServiceImpl implements NguoiDungService {

    @Autowired
    private INguoiDung nguoiDungRepository;

    @Override
    public List<NguoiDung> getAllNguoiDung() {
        return nguoiDungRepository.findAll();
    }

    @Override
    public NguoiDung getNguoiDungById(int id) {
        return nguoiDungRepository.findById(id).orElse(null);
    }

    @Override
    public void saveNguoiDung(NguoiDung nguoiDung) {
        nguoiDungRepository.save(nguoiDung);
    }

    @Override
    public void deleteNguoiDung(int id) {
        nguoiDungRepository.deleteById(id);
    }
    
    @Override
    public NguoiDung getNguoiDungByTenDangNhap(String tenDangNhap) {
        return nguoiDungRepository.findByTenDangNhap(tenDangNhap);
    }
    
    @Override
    public void updateEnabledStatus(int id, boolean enabled) {
        nguoiDungRepository.updateEnabledStatus(id, enabled);
    }
}