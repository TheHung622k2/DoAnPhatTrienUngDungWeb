package com.thi.cuoiky.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "loai_do_kem_them")
public class LoaiDoKemThem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_loai_do_kem_them")
    private Integer maLoaiDoKemThem;

    @Column(name = "ten_loai", nullable = false, length = 50)
    private String tenLoai;

    // Constructors, getters, and setters
    public LoaiDoKemThem() {
    }

    public Integer getMaLoaiDoKemThem() {
        return maLoaiDoKemThem;
    }

    public void setMaLoaiDoKemThem(Integer maLoaiDoKemThem) {
        this.maLoaiDoKemThem = maLoaiDoKemThem;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}