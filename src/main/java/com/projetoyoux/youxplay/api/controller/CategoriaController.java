package com.projetoyoux.youxplay.api.controller;

import com.projetoyoux.youxplay.api.dto.CategoriaDTO;
import com.projetoyoux.youxplay.exception.RegraNegocioException;
import com.projetoyoux.youxplay.model.entity.Usuario;
import com.projetoyoux.youxplay.model.entity.lancamentos.Categoria;
import com.projetoyoux.youxplay.service.CategoriaService;
import com.projetoyoux.youxplay.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private CategoriaService service;
    private UsuarioService usuarioService;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody CategoriaDTO dto){
        try{
            Categoria entidade = converter(dto);
            entidade = service.salvar(entidade);
            return ResponseEntity.ok(entidade);
        }catch (RegraNegocioException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar( @PathVariable("id") Long id, @RequestBody CategoriaDTO dto ) {
        return service.obterPorId(id).map( entity -> {
            try {
                Categoria categoria = converter(dto);
                categoria.setId(entity.getId());
                service.atualizar(categoria);
                return ResponseEntity.ok(categoria);
            }catch (RegraNegocioException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }).orElseGet( () ->
                new ResponseEntity("Lancamento não encontrado na base de Dados.", HttpStatus.BAD_REQUEST) );
    }

    private Categoria converter(CategoriaDTO dto){
        Categoria categoria = new Categoria();
        categoria.setId(dto.getId());
        categoria.setNome(dto.getNome());

        Usuario usuario = usuarioService
                .obterPorId(dto.getUsuario())
                .orElseThrow(() -> new RegraNegocioException("Usuário não encontrado para o Id informado."));

        categoria.setUsuario(usuario);
        return categoria;
    }

}
