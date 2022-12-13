package com.veda_tienda_2.service;

import com.veda_tienda_2.model.Usuario;
import java.util.List;
import java.util.Optional;


public interface UsuarioService {
    Optional<Usuario> findById(Integer id);
    Usuario save(Usuario usuario);
    Optional<Usuario> findByEmail(String email);
    List<Usuario>findAll();
}
