package com.thi.cuoiky.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thi.cuoiky.entities.MonKem;
import com.thi.cuoiky.repositories.IMonKem;

@Service
public class MonKemServiceImpl implements MonKemService {

    @Autowired
    private IMonKem monKemRepository;

    @Override
    public List<MonKem> getAllMonKem() {
        return monKemRepository.findAll();
    }

    @Override
    public MonKem getMonKemById(int id) {
        return monKemRepository.findById(id).orElse(null);
    }

    @Override
    public void saveMonKem(MonKem monKem) {
        monKemRepository.save(monKem);
    }

    @Override
    public void deleteMonKem(int id) {
        monKemRepository.deleteById(id);
    }
}