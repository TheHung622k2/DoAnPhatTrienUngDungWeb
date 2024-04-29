package com.thi.cuoiky.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "phim")
public class Phim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_phim", nullable = false)
    private Integer maPhim;

    @Column(name = "ten_phim", nullable = false, length = 255)
    private String tenPhim;

    @Column(name = "anh", nullable = false, length = 255)
    private String anh;

    @Column(name = "dao_dien", nullable = false, length = 255)
    private String daoDien;

    @Column(name = "dien_vien", nullable = false, length = 255)
    private String dienVien;

    @Column(name = "thoi_luong", nullable = false, length = 50)
    private String thoiLuong;

    @Column(name = "khoi_chieu", nullable = false, length = 100)
    private String khoiChieu;

    @Column(name = "mo_ta", length = 500)
    private String moTa;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "phim_the_loai",
            joinColumns = @JoinColumn(name = "ma_phim"),
            inverseJoinColumns = @JoinColumn(name = "ma_the_loai")
    )
    private List<TheLoai> theLoai = new ArrayList<>();

    // Constructors, getters, and setters
    public Phim() {
    }

    public Integer getMaPhim() {
        return maPhim;
    }

    public void setMaPhim(Integer maPhim) {
        this.maPhim = maPhim;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getDaoDien() {
        return daoDien;
    }

    public void setDaoDien(String daoDien) {
        this.daoDien = daoDien;
    }

    public String getDienVien() {
        return dienVien;
    }

    public void setDienVien(String dienVien) {
        this.dienVien = dienVien;
    }

    public String getThoiLuong() {
        return thoiLuong;
    }

    public void setThoiLuong(String thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

    public String getKhoiChieu() {
        return khoiChieu;
    }

    public void setKhoiChieu(String khoiChieu) {
        this.khoiChieu = khoiChieu;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
    
    public List<TheLoai> getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(List<TheLoai> list) {
        this.theLoai = list;
    }
}