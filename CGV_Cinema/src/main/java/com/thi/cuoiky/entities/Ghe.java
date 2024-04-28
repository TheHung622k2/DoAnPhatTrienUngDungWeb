package com.thi.cuoiky.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ghe")
public class Ghe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_ghe")
    private Integer maGhe;

    @Column(name = "ten_ghe", nullable = false, length = 20)
    private String tenGhe;

    // Constructors, getters, and setters
    public Ghe() {
    }

    public Integer getMaGhe() {
        return maGhe;
    }

    public void setMaGhe(Integer maGhe) {
        this.maGhe = maGhe;
    }

    public String getTenGhe() {
        return tenGhe;
    }

    public void setTenGhe(String tenGhe) {
        this.tenGhe = tenGhe;
    }
}