package com.thi.cuoiky.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thi.cuoiky.entities.TheLoai;
import com.thi.cuoiky.repositories.ITheLoai;

@Service
public class TheLoaiServiceImpl implements TheLoaiService {

    @Autowired
    private ITheLoai theLoaiRepository;

    @Override
    public List<TheLoai> getAllTheLoai() {
        return theLoaiRepository.findAll();
    }

    @Override
    public TheLoai getTheLoaiById(int id) {
        return theLoaiRepository.findById(id).orElse(null);
    }

    @Override
    public void saveTheLoai(TheLoai theLoai) {
        theLoaiRepository.save(theLoai);
    }

    @Override
    public void deleteTheLoai(int id) {
        theLoaiRepository.deleteById(id);
    }
}
