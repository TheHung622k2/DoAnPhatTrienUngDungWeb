package com.thi.cuoiky.entities;

import java.io.Serializable;
import java.util.Objects;

public class PhimTheLoaiId implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer maPhim;       // Khóa chính phim
    private Integer maTheLoai;    // Khóa chính thể loại

    public PhimTheLoaiId() {
        super();
    }
    
    public PhimTheLoaiId(Integer maPhim, Integer maTheLoai) {
        this.maPhim = maPhim;
        this.maTheLoai = maTheLoai;
    }

    // Getters and setters
    public Integer getMaPhim() {
        return maPhim;
    }

    public void setMaPhim(Integer maPhim) {
        this.maPhim = maPhim;
    }

    public Integer getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(Integer maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    // equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhimTheLoaiId that = (PhimTheLoaiId) o;
        return Objects.equals(maPhim, that.maPhim) &&
               Objects.equals(maTheLoai, that.maTheLoai);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maPhim, maTheLoai);
    }
}
