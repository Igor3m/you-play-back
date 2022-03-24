package com.projetoyoux.youxplay.model.repository;

import com.projetoyoux.youxplay.model.entity.lancamentos.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
