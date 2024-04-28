package com.thi.cuoiky.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "phim_the_loai")
@IdClass(PhimTheLoaiId.class)
public class Phim_TheLoai {

    @Id
    @ManyToOne
    @JoinColumn(name = "ma_phim", referencedColumnName = "ma_phim", nullable = false)
    private Phim maPhim;

    @Id
    @ManyToOne
    @JoinColumn(name = "ma_the_loai", referencedColumnName = "ma_the_loai", nullable = false)
    private TheLoai maTheLoai;

	public Phim_TheLoai() {
		super();
	}

	public Phim getMaPhim() {
		return maPhim;
	}

	public void setMaPhim(Phim maPhim) {
		this.maPhim = maPhim;
	}

	public TheLoai getMaTheLoai() {
		return maTheLoai;
	}

	public void setMaTheLoai(TheLoai maTheLoai) {
		this.maTheLoai = maTheLoai;
	}
}
