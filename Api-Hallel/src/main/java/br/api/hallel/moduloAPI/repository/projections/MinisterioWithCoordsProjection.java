package br.api.hallel.moduloAPI.repository.projections;

import br.api.hallel.moduloAPI.model.Membro;

import java.util.ArrayList;

public record MinisterioWithCoordsProjection(String id, String nome,
                                             Membro coordenador,
                                             Membro viceCoordenador) {
}
