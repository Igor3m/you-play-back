package com.projetoyoux.youxplay.service;

import com.projetoyoux.youxplay.model.entity.lancamentos.Categoria;
import com.projetoyoux.youxplay.model.entity.lancamentos.Video;

import java.util.List;

public interface VideoService {

    Video salvar(Video video);

    Video atualizar(Video video);

    void deletar(Video video);

    List<Video> buscar(Video videoFiltro);

    void validar(Video video);

}
