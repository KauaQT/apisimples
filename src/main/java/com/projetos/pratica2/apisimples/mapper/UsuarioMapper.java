package com.projetos.pratica2.apisimples.mapper;

import com.projetos.pratica2.apisimples.dto.UsuarioDto;
import com.projetos.pratica2.apisimples.entity.Usuario;

public class UsuarioMapper {

    public static UsuarioDto usuarioDto(Usuario entity) {

        UsuarioDto usuarioDto = new UsuarioDto();

        usuarioDto.setEmail(entity.getEmail());
        usuarioDto.setSenha(entity.getSenha());

        return usuarioDto;
    }

    public static Usuario usuarioEntity(UsuarioDto dto) {

        Usuario usuario = new Usuario();

        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());

        return usuario;
    }
}
