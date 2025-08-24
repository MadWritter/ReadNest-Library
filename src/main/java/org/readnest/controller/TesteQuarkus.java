package org.readnest.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/teste")
public class TesteQuarkus {

    @GET
    public String teste() {
        return "Testando endpoint";
    }
}
