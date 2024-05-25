package com.thi.cuoiky.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thi.cuoiky.entities.PhimTheLoaiId;
import com.thi.cuoiky.entities.Phim_TheLoai;
import com.thi.cuoiky.repositories.IPhimTheLoai;

@Service
public class PhimTheLoaiServiceImpl implements PhimTheLoaiService {

    @Autowired
    private IPhimTheLoai phimTheLoaiRepository;

    @Override
    public List<Phim_TheLoai> getAllPhimTheLoai() {
        return phimTheLoaiRepository.findAll();
    }

    @Override
    public Phim_TheLoai getPhimTheLoaiById(PhimTheLoaiId id) {
        return phimTheLoaiRepository.findById(id).orElse(null);
    }

    @Override
    public void savePhimTheLoai(Phim_TheLoai phimTheLoai) {
        phimTheLoaiRepository.save(phimTheLoai);
    }

    @Override
    public void deletePhimTheLoai(PhimTheLoaiId id) {
        phimTheLoaiRepository.deleteById(id);
    }

    @Override
    public List<Phim_TheLoai> getPhimTheLoaiByPhimId(int phimId) {
        return phimTheLoaiRepository.findByMaPhim_MaPhim(phimId);
    }

    @Override
    public List<Phim_TheLoai> getPhimTheLoaiByTheLoaiId(int theLoaiId) {
        return phimTheLoaiRepository.findByMaTheLoai_MaTheLoai(theLoaiId);
    }
}
