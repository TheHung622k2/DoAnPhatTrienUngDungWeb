package com.thi.cuoiky.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thi.cuoiky.entities.Ve;
import com.thi.cuoiky.repositories.IVe;

@Service
public class VeServiceImpl implements VeService {

    @Autowired
    private IVe veRepository;

    @Override
    public List<Ve> getAllVe() {
        return veRepository.findAll();
    }

    @Override
    public Ve getVeById(int id) {
        return veRepository.findById(id).orElse(null);
    }

    @Override
    public void saveVe(Ve ve) {
        veRepository.save(ve);
    }

    @Override
    public void deleteVe(int id) {
        veRepository.deleteById(id);
    }

    @Override
    public List<Ve> getVeBySuatChieuId(int suatChieuId) {
        return veRepository.findBySuatChieu_MaSuatChieu(suatChieuId);
    }

    @Override
    public List<Ve> getVeByNguoiDungId(int nguoiDungId) {
        return veRepository.findByNguoiDung_MaNguoiDung(nguoiDungId);
    }
}