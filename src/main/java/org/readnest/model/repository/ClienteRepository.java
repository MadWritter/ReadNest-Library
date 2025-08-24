package org.readnest.model.repository;

import java.util.Optional;

import org.readnest.model.entity.Cliente;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class ClienteRepository implements PanacheRepository<Cliente> {

    public Optional<Cliente> findByIdAndAtivoTrue(Long id) {
        return find("id = :id AND ativo = true", Parameters.with("id", id)).firstResultOptional();
    }

    public PanacheQuery<Cliente> findAllByAtivoTrue() {
        return find("ativo = true");
    }
}
