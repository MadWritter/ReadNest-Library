package org.readnest.model.repository;

import org.readnest.model.entity.Cliente;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class ClienteRepository implements PanacheRepository<Cliente> {

}
