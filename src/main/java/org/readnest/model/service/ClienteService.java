package org.readnest.model.service;

import java.util.List;
import java.util.Optional;

import org.readnest.model.dto.DadosAtualizacaoCliente;
import org.readnest.model.dto.DadosCadastroCliente;
import org.readnest.model.dto.DadosClienteCadastrado;
import org.readnest.model.entity.Cliente;
import org.readnest.model.repository.ClienteRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;

@RequestScoped
public class ClienteService {

    @Inject
	private ClienteRepository clienteRepository;

	@Transactional(rollbackOn = PersistenceException.class)
    public DadosClienteCadastrado cadastrarCliente(DadosCadastroCliente dados) {
        String senhaCifrada = BCrypt.withDefaults().hashToString(12, dados.senha().toCharArray());
        Cliente novoCliente = new Cliente(dados, senhaCifrada);

        clienteRepository.persist(novoCliente);

        return new DadosClienteCadastrado(novoCliente);
    }

    public Optional<DadosClienteCadastrado> buscarCliente(Long id) {
        return clienteRepository.findByIdAndAtivoTrue(id).map(cliente -> new DadosClienteCadastrado(cliente));
    }

	public List<DadosClienteCadastrado> listarClientes(int page, int pageSize) {
	    return clienteRepository.findAllByAtivoTrue().page(page, pageSize).list().stream()
	        .map(cliente -> new DadosClienteCadastrado(cliente))
	        .toList();
	}

	@Transactional(rollbackOn = PersistenceException.class)
    public DadosClienteCadastrado atualizarCliente(Long id, DadosAtualizacaoCliente dadosDeAtualizacao) {
        Optional<Cliente> clienteConsultado = clienteRepository.findByIdAndAtivoTrue(id);

        if(clienteConsultado.isEmpty()) {
            throw new EntityNotFoundException("Não foi possível encontrar o cliente a partir do ID informado");
        }

        if(!dadosDeAtualizacao.email().isBlank()) {
            clienteConsultado.get().setEmail(dadosDeAtualizacao.email());
        }

        if(!dadosDeAtualizacao.usuario().isBlank()) {
            clienteConsultado.get().setUsuario(dadosDeAtualizacao.usuario());
        }

        if(!dadosDeAtualizacao.senha().isBlank()) {
            String senhaCifrada = BCrypt.withDefaults().hashToString(12, dadosDeAtualizacao.senha().toCharArray());
            clienteConsultado.get().setSenha(senhaCifrada);
        }

        clienteRepository.flush(); // Merge da att no DB
        return new DadosClienteCadastrado(clienteConsultado.get());
    }

    @Transactional(rollbackOn = PersistenceException.class)
    public boolean excluirCliente(Long id) {
        Optional<Cliente> clienteConsultado = clienteRepository.findByIdAndAtivoTrue(id);

        if(clienteConsultado.isEmpty()) {
            throw new EntityNotFoundException("Não foi possível encontrar o cliente a partir do ID informado");
        }

        clienteConsultado.get().setAtivo(false);

        try {
            clienteRepository.flush();
        } catch(PersistenceException e) {
            return false;
        }

        return true;
    }
}
