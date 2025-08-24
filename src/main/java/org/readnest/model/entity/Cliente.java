package org.readnest.model.entity;

import org.readnest.model.dto.DadosCadastroCliente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 50)
	private String nome;
	@Column(nullable = false, length = 50)
	private String email;
	@Column(nullable = false, length = 15)
	private String usuario;
	@Column(nullable = false, length = 50)
	private String senha;
	@Column(nullable = false)
	private Boolean status;

	public Cliente(String nome, String email, String usuario, String senha) {
	    setNome(nome);
		setEmail(email);
		setUsuario(usuario);
		setSenha(senha);
		setStatus(true);
	}

    public Cliente(DadosCadastroCliente dados) {
        this(dados.nome(), dados.email(), dados.usuario(), dados.senha());
    }

	public void setNome(String nome) {
	    if(nome != null && !nome.isBlank()) {
			this.nome = nome;
		}
	}

	public void setEmail(String email) {
		if(email != null && !email.isBlank()) {
			this.email = email;
		}
	}

	public void setUsuario(String usuario) {
		if(usuario != null && !usuario.isBlank()) {
			this.usuario = usuario;
		}
	}

	public void setSenha(String senha) {
		if(senha != null && !senha.isBlank()) {
			this.senha = senha;
		}
	}

	public void setStatus(Boolean status) {
		if(status != null) {
			this.status = status;
		}
	}


}
