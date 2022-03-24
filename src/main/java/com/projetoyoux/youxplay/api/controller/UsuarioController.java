package com.projetoyoux.youxplay.api.controller;

import com.projetoyoux.youxplay.api.dto.UsuarioDTO;
import com.projetoyoux.youxplay.exception.ErroAutenticacao;
import com.projetoyoux.youxplay.exception.RegraNegocioException;
import com.projetoyoux.youxplay.model.entity.Usuario;
import com.projetoyoux.youxplay.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    /*
    @GetMapping("/")
    public String helloWorld(){
        return "hello world";
    }
    */

    private UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping("/autenticar")
    public ResponseEntity autenticar(@RequestBody UsuarioDTO dto){

        try{
            Usuario usuarioAutenticado = service.autenticar(dto.getEmail(), dto.getSenha());
            return ResponseEntity.ok(usuarioAutenticado);
        }catch (ErroAutenticacao e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody UsuarioDTO dto){

        Usuario usuario = Usuario.builder()

                .nome(dto.getNome())
                .email(dto.getEmail())
                .senha(dto.getSenha()).build();

        try{
            Usuario usuarioSalvo = service.salvarUsuario(usuario);
            return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);

        }catch (RegraNegocioException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
