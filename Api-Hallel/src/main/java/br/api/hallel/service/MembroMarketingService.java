package br.api.hallel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.api.hallel.model.MembroMarketing;
import br.api.hallel.repository.MembroMarketingRepository;
import br.api.hallel.service.interfaces.MembroMarketingInterface;

@Service
public class MembroMarketingService implements MembroMarketingInterface {

    @Autowired
    private MembroMarketingRepository repository;

    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public MembroMarketing createMembroMarketing(MembroMarketing membroMarketing) {
        String encoder = this.passwordEncoder().encode(membroMarketing.getSenha());
        membroMarketing.setSenha(encoder);
        return this.repository.insert(membroMarketing);
    }

    @Override
    public List<MembroMarketing> listAllMembrosMarketing() {
        return this.repository.findAll();
    }

    @Override
    public MembroMarketing findMembroId(String id) {

        Optional<MembroMarketing> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            MembroMarketing membro = optional.get();

            System.out.println("Membro encontrado!");
            return membro;

        } else {
            System.out.println("Membro não encontrado!");
            return null;
        }

    }

    @Override
    public MembroMarketing updateById(String id) {
        Optional<MembroMarketing> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            MembroMarketing membro = optional.get();

            return this.repository.save(membro);

        } else {
            System.out.println("Membro não encontrado!");

            return null;
        }

    }

    @Override
    public void deleteById(String id) {
        Optional<MembroMarketing> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            MembroMarketing membro = optional.get();

            this.repository.deleteById(id);
            System.out.println("Membro deletado!");

        } else {
            System.out.println("Membro não encontrado!");

        }

    }

    @Override
    public MembroMarketing findByEmail(String email) {
        Optional<MembroMarketing> optional = this.repository.findByEmail(email);

        if (optional.isPresent()) {
            MembroMarketing membro = optional.get();

            return membro;

        } else {
            System.out.println("Membro não encontrado!");

            return null;
        }
    }

    @Override
    public MembroMarketing updateByEmail(String email) {
        Optional<MembroMarketing> optional = this.repository.findByEmail(email);

        if (optional.isPresent()) {
            MembroMarketing membro = optional.get();

            return this.repository.save(membro);

        } else {
            System.out.println("Membro não encontrado!");

            return null;
        }
    }

}
