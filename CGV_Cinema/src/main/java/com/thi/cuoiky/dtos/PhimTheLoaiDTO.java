package com.thi.cuoiky.dtos;

public class PhimTheLoaiDTO {
    private Integer maPhim;
    private String tenPhim;
    private String tenTheLoai;

    public PhimTheLoaiDTO(Integer maPhim, String tenPhim, String tenTheLoai) {
        this.maPhim = maPhim;
        this.tenPhim = tenPhim;
        this.tenTheLoai = tenTheLoai;
    }

    // Getters and setters
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

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }
}