package com.projetos.pratica2.apisimples.service;

import com.projetos.pratica2.apisimples.dto.UsuarioDto;
import com.projetos.pratica2.apisimples.entity.Usuario;
import com.projetos.pratica2.apisimples.mapper.UsuarioMapper;
import com.projetos.pratica2.apisimples.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public ResponseEntity<List<UsuarioDto>> findAll() {

        List<Usuario> listaUsuario = usuarioRepository.findAll();

        // Método de referência
        List<UsuarioDto> usuarioDtos = listaUsuario.stream().map(x -> UsuarioMapper.usuarioDto(x)).toList();

        if (usuarioDtos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(usuarioDtos);
    }

    public ResponseEntity<?> getById(UUID id) {
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findById(id);

        if (usuarioEncontrado.isEmpty()) {
            return ResponseEntity.status(400).build();
        }


        return ResponseEntity.status(200).body(usuarioEncontrado);
    }


    public ResponseEntity<UsuarioDto> save(UsuarioDto dto) {

        if (usuarioRepository.findByEmail(dto.getEmail())) {
            return ResponseEntity.status(409).build();
        }

//        Usuario usuario = UsuarioMapper.usuarioEntity(dto);
        Usuario usuarioSalvo = usuarioRepository.save(UsuarioMapper.usuarioEntity(dto));


        UsuarioDto usuarioConvertido = UsuarioMapper.usuarioDto(usuarioSalvo);

        return ResponseEntity.status(201).body(usuarioConvertido);
    }

    public ResponseEntity<Void> deleteById(UUID uuid) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(uuid);

        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.status(400).build();
        }

        usuarioRepository.deleteById(uuid);
        return ResponseEntity.status(200).build();
    }

    public ResponseEntity<UsuarioDto> update(UUID uuid, UsuarioDto dto) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(uuid);

        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.status(400).build();
        }

        UsuarioDto usuarioAtualizado = new UsuarioDto();

        usuarioAtualizado.setEmail(usuarioOptional.get().getEmail());
        usuarioAtualizado.setSenha(usuarioOptional.get().getSenha());

        return ResponseEntity.status(200).body(usuarioAtualizado);
    }

}
