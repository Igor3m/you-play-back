package com.projetoyoux.youxplay.service.impl;

import com.projetoyoux.youxplay.exception.ErroAutenticacao;
import com.projetoyoux.youxplay.exception.RegraNegocioException;
import com.projetoyoux.youxplay.model.entity.Usuario;
import com.projetoyoux.youxplay.model.repository.UsuarioRepository;
import com.projetoyoux.youxplay.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository repository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository repository) {
        super();
        this.repository = repository;
    }

    @Override
    public Usuario autenticar(String email, String senha) {
        Optional<Usuario> usuario = repository.findByEmail(email);

        if (!usuario.isPresent()) {

            throw new ErroAutenticacao("Usuario não encontrado para email informado.");
        }

        if(!usuario.get().getSenha().equals(senha)) {

            throw new ErroAutenticacao("Senha inválida.");

        }
        return usuario.get();
    }

    @Override
    @Transactional
    public Usuario salvarUsuario(Usuario usuario) {
        validarEmail(usuario.getEmail());
        return repository.save(usuario);
    }

    @Override
    public void validarEmail(String email) {

        boolean existe = repository.existsByEmail(email);
        if (existe){
            throw new RegraNegocioException("Já existe um usuário cadastrado com esse email.");
        }

    }

    @Override
    public Optional<Usuario> obterPorId(Long id) {

        return repository.findById(id);

    }
}
