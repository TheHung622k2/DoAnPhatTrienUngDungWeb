package com.thi.cuoiky.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thi.cuoiky.entities.VaiTro;
import com.thi.cuoiky.repositories.IVaiTro;

@Service
public class VaiTroServiceImpl implements VaiTroService {

    @Autowired
    private IVaiTro vaiTroRepository;

    @Override
    public List<VaiTro> getAllVaiTro() {
        return vaiTroRepository.findAll();
    }

    @Override
    public VaiTro getVaiTroById(int id) {
        return vaiTroRepository.findById(id).orElse(null);
    }

    @Override
    public void saveVaiTro(VaiTro vaiTro) {
        vaiTroRepository.save(vaiTro);
    }

    @Override
    public void deleteVaiTro(int id) {
        vaiTroRepository.deleteById(id);
    }
}