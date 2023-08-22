package br.api.hallel.moduloAPI.service.interfaces;

import br.api.hallel.moduloAPI.model.MembroMarketing;

import java.util.List;

public interface MembroMarketingInterface {

    public MembroMarketing createMembroMarketing(MembroMarketing membro);

    public List<MembroMarketing> listAllMembrosMarketing();

    public MembroMarketing findMembroId(String id);

    public MembroMarketing updateById(String id);

    public MembroMarketing updateByEmail(String email);

    public void deleteById(String id);

    public MembroMarketing findByEmail(String email);

}