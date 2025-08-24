package org.readnest.controller;

import java.net.URI;

import org.readnest.model.dto.DadosAtualizacaoCliente;
import org.readnest.model.dto.DadosCadastroCliente;
import org.readnest.model.dto.DadosClienteCadastrado;
import org.readnest.model.service.ClienteService;

import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("/cliente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteController {

    @Inject
	private ClienteService clienteService;

	@POST
	public Response cadastrarCliente(@Valid DadosCadastroCliente dados, @Context UriInfo uriInfo) {
        DadosClienteCadastrado dadosCadastrados;
        try {
            dadosCadastrados = clienteService.cadastrarCliente(dados);
        } catch(PersistenceException e) {
            return Response.status(500, "Não foi possível processar a solicitação, tente novamente em instantes").build();
        }

		URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(dadosCadastrados.id())).build();

		return Response.created(uri).entity(dadosCadastrados).build();
	}

	@GET
	@Path("/{id}")
	public Response buscarCliente(@PathParam("id") Long id) {
		var clienteConsultado = clienteService.buscarCliente(id);

		if(clienteConsultado.isEmpty()) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		return Response.ok(clienteConsultado.get()).build();
	}

	@GET
	public Response listarClientes(@QueryParam("page") @DefaultValue("0")int page,
	                               @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
		var clientesConsultados = clienteService.listarClientes(page, pageSize);

		return Response.ok(clientesConsultados).build();
	}

	@PUT
	@Path("/{id}")
	public Response atualizarCliente(@PathParam("id") Long id,
	                                 @Valid DadosAtualizacaoCliente dadosDeAtualizacao) {
        DadosClienteCadastrado dadosAtualizados;

        try {
            dadosAtualizados = clienteService.atualizarCliente(id, dadosDeAtualizacao);
        } catch(EntityNotFoundException e) {
            return Response.status(404, e.getMessage()).build();
        } catch(PersistenceException e) {
            return Response.status(500, "Não foi possível processar a solicitação, tente novamente em instantes").build();
        }

        return Response.ok(dadosAtualizados).build();
	}

	@DELETE
	@Path("/{id}")
	public Response excluirCliente(@PathParam("id") Long id) {
	    boolean excluiu;

	    try {
	        excluiu = clienteService.excluirCliente(id);
		} catch(EntityNotFoundException e) {
		    return Response.status(404, e.getMessage()).build();
		}

		if(!excluiu) {
		    return Response.status(500, "Não foi possível processar a solicitação, tente novamente em instantes").build();
		}

		return Response.noContent().build();
	}
}
