package org.readnest.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record DadosCadastroCliente(

    @NotBlank(message = "Nome é obrigatório")
    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ\\s]+$", message = "O nome deve conter apenas letras e espaços")
    @Size(min = 3, max = 50, message = "O nome deve conter entre 3 e 50 caracteres")
    String nome,
    @NotBlank(message = "Email é obrigatório")
    @Email
    @Size(min = 10, max = 50, message = "O email deve conter entre 10 e 50 caracteres")
    String email,
    @NotBlank(message = "O campo de usuário é obrigatório")
    @Size(min = 5, max = 15, message = "O usuário deve conter entre 5 e 15 caracteres")
    String usuario,
    @NotBlank(message = "Obrigatório informar a senha")
    @Size(min = 10, message = "A senha deve conter pelo menos 10 caracteres")
    String senha

) {

}
