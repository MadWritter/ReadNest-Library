package org.readnest.controller;

import java.net.URI;

import org.readnest.model.dto.DadosCadastroCliente;
import org.readnest.model.service.ClienteService;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("/cliente")
public class ClienteController {

    @Inject
	private ClienteService clienteService;

	@POST
	public Response cadastrarCliente(@Valid DadosCadastroCliente dados, @Context UriInfo uriInfo) {
	    var dadosCadastrados = clienteService.cadastrarCliente(dados);

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
}
