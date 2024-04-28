package com.thi.cuoiky.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "suat_chieu")
public class SuatChieu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_suat_chieu")
    private Integer maSuatChieu;

    @ManyToOne
    @JoinColumn(name = "ma_phim", nullable = false)
    private Phim phim;

    @ManyToOne
    @JoinColumn(name = "ma_phong", nullable = false)
    private PhongChieu phongChieu;

    @Column(name = "thoi_gian_chieu", nullable = false)
    private LocalDateTime thoiGianChieu;

    // Constructors, getters, and setters
    public SuatChieu() {
    }

    public Integer getMaSuatChieu() {
        return maSuatChieu;
    }

    public void setMaSuatChieu(Integer maSuatChieu) {
        this.maSuatChieu = maSuatChieu;
    }

    public Phim getPhim() {
        return phim;
    }

    public void setPhim(Phim phim) {
        this.phim = phim;
    }

    public PhongChieu getPhongChieu() {
        return phongChieu;
    }

    public void setPhongChieu(PhongChieu phongChieu) {
        this.phongChieu = phongChieu;
    }

    public LocalDateTime getThoiGianChieu() {
        return thoiGianChieu;
    }

    public void setThoiGianChieu(LocalDateTime thoiGianChieu) {
        this.thoiGianChieu = thoiGianChieu;
    }
}