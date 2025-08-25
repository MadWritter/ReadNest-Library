package org.readnest.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record DadosAtualizacaoCliente(
    @Email
    @Size(min = 10, max = 50, message = "O email deve conter entre 10 e 50 caracteres")
    String email,
    @Size(min = 5, max = 15, message = "O usuário deve conter entre 5 e 15 caracteres")
    String usuario,
    @Size(min = 10, message = "A senha deve conter pelo menos 10 caracteres")
    String senha
) {

}
