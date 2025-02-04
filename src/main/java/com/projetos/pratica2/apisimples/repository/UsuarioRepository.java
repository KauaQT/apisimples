package com.projetos.pratica2.apisimples.repository;

import com.projetos.pratica2.apisimples.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    boolean findByEmail(String email);
}
