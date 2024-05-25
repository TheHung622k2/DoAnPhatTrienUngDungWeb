package com.thi.cuoiky.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thi.cuoiky.entities.PhongChieu;
import com.thi.cuoiky.repositories.IPhongChieu;

@Service
public class PhongChieuServiceImpl implements PhongChieuService {

    @Autowired
    private IPhongChieu phongChieuRepository;

    @Override
    public List<PhongChieu> getAllPhongChieu() {
        return phongChieuRepository.findAll();
    }

    @Override
    public PhongChieu getPhongChieuById(int id) {
        return phongChieuRepository.findById(id).orElse(null);
    }

    @Override
    public void savePhongChieu(PhongChieu phongChieu) {
        phongChieuRepository.save(phongChieu);
    }

    @Override
    public void deletePhongChieu(int id) {
        phongChieuRepository.deleteById(id);
    }
}
