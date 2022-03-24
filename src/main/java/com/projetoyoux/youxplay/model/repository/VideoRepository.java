package com.projetoyoux.youxplay.model.repository;

import com.projetoyoux.youxplay.model.entity.lancamentos.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
