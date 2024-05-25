package com.thi.cuoiky.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thi.cuoiky.entities.HoaDon;
import com.thi.cuoiky.repositories.IHoaDon;

@Service
public class HoaDonServiceImpl implements HoaDonService {

    @Autowired
    private IHoaDon hoaDonRepository;

    @Override
    public List<HoaDon> getAllHoaDon() {
        return hoaDonRepository.findAll();
    }

    @Override
    public HoaDon getHoaDonById(int id) {
        return hoaDonRepository.findById(id).orElse(null);
    }

    @Override
    public void saveHoaDon(HoaDon hoaDon) {
        hoaDonRepository.save(hoaDon);
    }

    @Override
    public void deleteHoaDon(int id) {
        hoaDonRepository.deleteById(id);
    }

    @Override
    public List<HoaDon> getHoaDonByVeId(int veId) {
        return hoaDonRepository.findByVe_MaVe(veId);
    }
}