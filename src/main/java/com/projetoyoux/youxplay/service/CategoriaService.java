package com.projetoyoux.youxplay.service;

import com.projetoyoux.youxplay.model.entity.lancamentos.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {

    Categoria salvar(Categoria categoria);

    Categoria atualizar(Categoria categoria);

    void deletar(Categoria categoria);

    List<Categoria> buscar( Categoria categoriaFiltro);

    void validar(Categoria categoria);

    Optional<Categoria> obterPorId(Long id);
}
