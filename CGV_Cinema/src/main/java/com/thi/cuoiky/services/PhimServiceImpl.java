package com.thi.cuoiky.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thi.cuoiky.entities.Phim;
import com.thi.cuoiky.entities.TheLoai;
import com.thi.cuoiky.repositories.IPhim;

@Service
public class PhimServiceImpl implements PhimService{

	@Autowired
	private IPhim phimRepository;

    @Override
    public List<Phim> getAllPhim() {
        return phimRepository.findAll();
    }

    @Override
    public Phim getPhimById(int id) {
        return phimRepository.findById(id).orElse(null);
    }

    @Override
    public void savePhim(Phim phim) {
        phimRepository.save(phim);
    }

    @Override
    public void deletePhim(int id) {
        phimRepository.deleteById(id);
    }

	@Override
	public List<TheLoai> layTheLoaiCuaPhim(int maPhim) {
		return phimRepository.layTheLoaiCuaPhim(maPhim);
	}
}
