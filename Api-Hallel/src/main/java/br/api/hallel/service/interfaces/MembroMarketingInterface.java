package br.api.hallel.service.interfaces;

import java.util.List;

import br.api.hallel.model.MembroMarketing;

public interface MembroMarketingInterface {

    public MembroMarketing createMembroMarketing(MembroMarketing membro);

    public List<MembroMarketing> listAllMembrosMarketing();

    public MembroMarketing findMembroId(String id);

    public MembroMarketing updateById(String id);

    public MembroMarketing updateByEmail(String email);

    public void deleteById(String id);

    public MembroMarketing findByEmail(String email);

}