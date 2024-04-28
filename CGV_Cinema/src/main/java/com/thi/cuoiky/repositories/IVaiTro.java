package com.thi.cuoiky.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thi.cuoiky.entities.VaiTro;

public interface IVaiTro extends JpaRepository<VaiTro, Integer> {
    // Các phương thức tùy chỉnh cho VaiTro
}