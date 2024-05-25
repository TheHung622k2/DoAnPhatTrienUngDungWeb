package com.thi.cuoiky.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thi.cuoiky.entities.LoaiDoKemThem;
import com.thi.cuoiky.repositories.ILoaiDoKemThem;

@Service
public class LoaiDoKemThemServiceImpl implements LoaiDoKemThemService {

    @Autowired
    private ILoaiDoKemThem loaiDoKemThemRepository;

    @Override
    public List<LoaiDoKemThem> getAllLoaiDoKemThem() {
        return loaiDoKemThemRepository.findAll();
    }

    @Override
    public LoaiDoKemThem getLoaiDoKemThemById(int id) {
        return loaiDoKemThemRepository.findById(id).orElse(null);
    }

    @Override
    public void saveLoaiDoKemThem(LoaiDoKemThem loaiDoKemThem) {
        loaiDoKemThemRepository.save(loaiDoKemThem);
    }

    @Override
    public void deleteLoaiDoKemThem(int id) {
        loaiDoKemThemRepository.deleteById(id);
    }
}
