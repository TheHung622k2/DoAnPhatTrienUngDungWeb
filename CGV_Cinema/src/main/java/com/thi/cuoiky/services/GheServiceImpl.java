package com.thi.cuoiky.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thi.cuoiky.entities.Ghe;
import com.thi.cuoiky.repositories.IGhe;

@Service
public class GheServiceImpl implements GheService {
	@Autowired
    private IGhe gheRepository;

    @Override
    public List<Ghe> getAllGhe() {
        return gheRepository.findAll();
    }

    @Override
    public Ghe getGheById(int id) {
        return gheRepository.findById(id).orElse(null);
    }

    @Override
    public void saveGhe(Ghe ghe) {
        gheRepository.save(ghe);
    }

    @Override
    public void deleteGhe(int id) {
        gheRepository.deleteById(id);
    }
}
