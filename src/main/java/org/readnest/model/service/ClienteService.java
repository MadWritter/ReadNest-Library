package org.readnest.model.service;

import java.util.List;
import java.util.Optional;
import org.readnest.model.dto.DadosCadastroCliente;
import org.readnest.model.dto.DadosClienteCadastrado;
import org.readnest.model.entity.Cliente;
import org.readnest.model.repository.ClienteRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RequestScoped
public class ClienteService {

    @Inject
	private ClienteRepository clienteRepository;

	@Transactional
    public DadosClienteCadastrado cadastrarCliente(@Valid DadosCadastroCliente dados) {
        Cliente novoCliente = new Cliente(dados);

        clienteRepository.persist(novoCliente);

        return new DadosClienteCadastrado(novoCliente);
    }

    public Optional<DadosClienteCadastrado> buscarCliente(Long id) {
        return clienteRepository.findByIdOptional(id).map(cliente -> new DadosClienteCadastrado(cliente));
    }

	public List<DadosClienteCadastrado> listarClientes(int page, int pageSize) {
	    return clienteRepository.findAll().page(page, pageSize).list().stream()
	        .map(cliente -> new DadosClienteCadastrado(cliente))
	        .toList();
	}
}
