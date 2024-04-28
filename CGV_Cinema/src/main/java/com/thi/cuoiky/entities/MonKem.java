package com.thi.cuoiky.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "mon_kem")
public class MonKem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_mon_kem")
    private Integer maMonKem;

    @Column(name = "ten_mon_kem", nullable = false, length = 255)
    private String tenMonKem;

    @ManyToOne
    @JoinColumn(name = "ma_loai_do_kem_them", nullable = false)
    private LoaiDoKemThem loaiDoKemThem;

    @Column(name = "gia_ban", nullable = false, precision = 10, scale = 2)
    private BigDecimal giaBan;

    @Column(name = "mo_ta", length = 500)
    private String moTa;

    @Column(name = "hinh_anh", length = 255)
    private String hinhAnh;

    // Constructors, getters, and setters
    public MonKem() {
    }

    public Integer getMaMonKem() {
        return maMonKem;
    }

    public void setMaMonKem(Integer maMonKem) {
        this.maMonKem = maMonKem;
    }

    public String getTenMonKem() {
        return tenMonKem;
    }

    public void setTenMonKem(String tenMonKem) {
        this.tenMonKem = tenMonKem;
    }

    public LoaiDoKemThem getLoaiDoKemThem() {
        return loaiDoKemThem;
    }

    public void setLoaiDoKemThem(LoaiDoKemThem loaiDoKemThem) {
        this.loaiDoKemThem = loaiDoKemThem;
    }

    public BigDecimal getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(BigDecimal giaBan) {
        this.giaBan = giaBan;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
}