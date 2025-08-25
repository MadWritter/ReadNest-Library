package org.readnest.model.dto;

import org.readnest.model.entity.Cliente;

public record DadosClienteCadastrado(
    Long id,
    String nome,
    String usuario
) {
    public DadosClienteCadastrado(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getUsuario());
    }
}
