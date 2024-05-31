package com.thi.cuoiky.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thi.cuoiky.entities.NguoiDung;

@Repository
public interface INguoiDung extends JpaRepository<NguoiDung, Integer> {
    NguoiDung findByTenDangNhap(String tenDangNhap);

    @Modifying
    @Transactional
    @Query("UPDATE NguoiDung n SET n.enabled = :enabled WHERE n.maNguoiDung = :maNguoiDung")
    void updateEnabledStatus(@Param("maNguoiDung") Integer maNguoiDung, @Param("enabled") Boolean enabled);
}