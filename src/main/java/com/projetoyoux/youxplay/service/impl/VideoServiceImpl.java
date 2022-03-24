package com.projetoyoux.youxplay.service.impl;

import com.projetoyoux.youxplay.exception.RegraNegocioException;
import com.projetoyoux.youxplay.model.entity.lancamentos.Video;
import com.projetoyoux.youxplay.model.repository.VideoRepository;
import com.projetoyoux.youxplay.service.VideoService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

public class VideoServiceImpl implements VideoService {

    VideoRepository repository;

    public VideoServiceImpl(VideoRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Video salvar(Video video) {
        return repository.save(video);
    }

    @Override
    @Transactional
    public Video atualizar(Video video) {
        Objects.requireNonNull(video.getId());
        return repository.save(video);
    }

    @Override
    @Transactional
    public void deletar(Video video) {

        Objects.requireNonNull(video.getId());
        repository.delete(video);

    }

    @Override
    @Transactional(readOnly = true)
    public List<Video> buscar(Video videoFiltro) {
        Example example = Example.of( videoFiltro,
                ExampleMatcher.matching()
                        .withIgnoreCase()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
        return repository.findAll(example);
    }

    @Override
    public void validar(Video video) {

        if(video.getNome() == null || video.getNome().trim().equals("")) {
            throw new RegraNegocioException("Informe um nome válido.");
        }

        if(video.getCategoria() == null || video.getCategoria().getId() == null) {
            throw new RegraNegocioException("Informe uma categoria.");
        }

    }
}
