package com.thi.cuoiky.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thi.cuoiky.entities.Phim;
import com.thi.cuoiky.entities.TheLoai;
import com.thi.cuoiky.repositories.IPhim;
import com.thi.cuoiky.repositories.ISuatChieu;

@Service
public class PhimServiceImpl implements PhimService{

	@Autowired
	private IPhim phimRepository;
	
	@Autowired
	private ISuatChieu suatChieuRepository;

    @Override
    public List<Phim> getAllPhim() {
        return phimRepository.findAll();
    }
    
    @Override
    public Page<Phim> getAllPhim(Pageable pageable) {
        return phimRepository.findAll(pageable);
    }
    
    @Override
    public Page<Phim> getAllPhim(String search, Pageable pageable) {
        if (search != null && !search.isEmpty()) {
            return phimRepository.findByTenPhimContainingIgnoreCase(search, pageable);
        }
        return phimRepository.findAll(pageable);
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
    	// Xóa các suất chiếu liên quan trước
    	suatChieuRepository.deleteByMaPhim(id);
        // Sau đó xóa phim
        phimRepository.deleteById(id);
    }

	@Override
	public List<TheLoai> layTheLoaiCuaPhim(int maPhim) {
		return phimRepository.layTheLoaiCuaPhim(maPhim);
	}
}
