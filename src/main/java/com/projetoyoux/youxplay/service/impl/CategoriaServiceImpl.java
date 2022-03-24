package com.projetoyoux.youxplay.service.impl;

import com.projetoyoux.youxplay.exception.RegraNegocioException;
import com.projetoyoux.youxplay.model.entity.lancamentos.Categoria;
import com.projetoyoux.youxplay.model.repository.CategoriaRepository;
import com.projetoyoux.youxplay.service.CategoriaService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CategoriaServiceImpl implements CategoriaService {

    private CategoriaRepository repository;

    public CategoriaServiceImpl(CategoriaRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Categoria salvar(Categoria categoria) {
        validar(categoria);
        return repository.save(categoria);
    }

    @Override
    @Transactional
    public Categoria atualizar(Categoria categoria) {
        Objects.requireNonNull(categoria.getId());
        validar(categoria);
        return repository.save(categoria);
    }

    @Override
    @Transactional
    public void deletar(Categoria categoria) {
        Objects.requireNonNull(categoria.getId());
        repository.delete(categoria);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> buscar(Categoria categoriaFiltro) {
        Example example = Example.of( categoriaFiltro,
                ExampleMatcher.matching()
                        .withIgnoreCase()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
        return repository.findAll(example);
    }

    @Override
    public void validar(Categoria categoria) {

        if(categoria.getNome() == null || categoria.getNome().trim().equals("")) {
            throw new RegraNegocioException("Informe um nome válido.");
        }

        if(categoria.getUsuario() == null || categoria.getUsuario().getId() == null) {
            throw new RegraNegocioException("Informe um Usuário.");
        }
    }

    @Override
    public Optional<Categoria> obterPorId(Long id) {
        return repository.findById(id);
    }
}
