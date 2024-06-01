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
@Table(name = "ve")
public class Ve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_ve")
    private Integer maVe;

    @ManyToOne
    @JoinColumn(name = "ma_suat_chieu", nullable = false)
    private SuatChieu suatChieu;

    @ManyToOne // Mỗi vé có một chỗ ngồi cụ thể trong rạp chiếu phim, nhưng một chỗ ngồi có thể được đặt bởi nhiều vé khác nhau trong các suất chiếu khác nhau.
    @JoinColumn(name = "ma_ghe", nullable = false)
    private Ghe ghe;

    @ManyToOne
    @JoinColumn(name = "ma_nguoi_dung", nullable = false)
    private NguoiDung nguoiDung;

    @ManyToOne
    @JoinColumn(name = "ma_mon_kem")
    private MonKem monKem;

    @Column(name = "gia_ve", nullable = false, precision = 10, scale = 2)
    private BigDecimal giaVe;
    
    @Column(name = "random_string", nullable = false, length = 8)
    private String randomString;

    // Constructors, getters, and setters
    public Ve() {
    }

    public Integer getMaVe() {
        return maVe;
    }

    public void setMaVe(Integer maVe) {
        this.maVe = maVe;
    }

    public SuatChieu getSuatChieu() {
        return suatChieu;
    }

    public void setSuatChieu(SuatChieu suatChieu) {
        this.suatChieu = suatChieu;
    }

    public Ghe getGhe() {
        return ghe;
    }

    public void setGhe(Ghe ghe) {
        this.ghe = ghe;
    }

    public NguoiDung getNguoiDung() {
        return nguoiDung;
    }

    public void setNguoiDung(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    public MonKem getMonKem() {
        return monKem;
    }

    public void setMonKem(MonKem monKem) {
        this.monKem = monKem;
    }

    public BigDecimal getGiaVe() {
        return giaVe;
    }

    public void setGiaVe(BigDecimal giaVe) {
        this.giaVe = giaVe;
    }


    public String getRandomString() {
    	return randomString;
    }

    public void setRandomString(String randomString) {
    	this.randomString = randomString;
    }
}