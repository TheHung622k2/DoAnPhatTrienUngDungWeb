package com.thi.cuoiky.entities;

import java.io.Serializable;

public class PhimTheLoaiId implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer maPhim;       // Khóa chính phim
    private Integer maTheLoai;    // Khóa chính thể loại

    public PhimTheLoaiId() {
        super();
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
}
