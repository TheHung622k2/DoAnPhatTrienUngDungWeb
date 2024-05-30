package com.thi.cuoiky.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

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

    // Phương thức để lấy tên suất chiếu
    public String getTenSuatChieu() {
        return this.phim.getTenPhim() + " - " + this.phongChieu.getTenPhong() + " - " + this.thoiGianChieu.toString();
    }
    
    // Phương thức để lấy thời gian chiếu đã được định dạng bao gồm thứ
    public String getFormattedThoiGianChieu() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String dayOfWeek = this.thoiGianChieu.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("vi"));
        String month = this.thoiGianChieu.getMonth().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("vi"));
        return dayOfWeek + ", " + this.thoiGianChieu.getDayOfMonth() + " " + month + " " + this.thoiGianChieu.getYear() + ", " + this.thoiGianChieu.format(timeFormatter);
    }
}
