package br.api.hallel.service;

import br.api.hallel.model.MembroMarketing;
import br.api.hallel.repository.MembroMarketingRepository;
import br.api.hallel.service.interfaces.MembroMarketingInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MembroMarketingService implements MembroMarketingInterface {

    @Autowired
    private MembroMarketingRepository repository;

    private Logger logger = LoggerFactory.getLogger(MembroMarketingService.class);

    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public MembroMarketing createMembroMarketing(MembroMarketing membroMarketing) {
        String encoder = this.passwordEncoder().encode(membroMarketing.getSenha());
        membroMarketing.setSenha(encoder);
        logger.info("Membro de Marketing Criado! -- Adminitrador: " + getLogado());
        return this.repository.insert(membroMarketing);
    }

    @Override
    public List<MembroMarketing> listAllMembrosMarketing() {
        logger.info("Membros de Marketing listados! -- Adminitrador: " + getLogado());

        return this.repository.findAll();
    }

    @Override
    public MembroMarketing findMembroId(String id) {

        Optional<MembroMarketing> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            MembroMarketing membro = optional.get();
            logger.info("Membro de Marketing:" + membro.getNome() + " listado -- Adminitrador: " + getLogado());

            return membro;

        } else {
            logger.error("Membro não encontrado! -- Adminitrador: " + getLogado());

            return null;
        }

    }

    @Override
    public MembroMarketing updateById(String id) {
        Optional<MembroMarketing> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            MembroMarketing membro = optional.get();
            logger.info("Membro de Marketing:" + membro.getNome() + " Alterado -- Adminitrador: " + getLogado());

            return this.repository.save(membro);

        } else {
            logger.error("Membro não encontrado! -- Adminitrador: " + getLogado());

            System.out.println("Membro não encontrado!");

            return null;
        }

    }

    @Override
    public void deleteById(String id) {
        Optional<MembroMarketing> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            MembroMarketing membro = optional.get();
            logger.info("Membro de Marketing:" + membro.getNome() + " deletado -- Adminitrador: " + getLogado());

            this.repository.deleteById(id);

        } else {
            logger.error("Membro  não encontrado! -- Adminitrador: " + getLogado());

        }

    }

    @Override
    public MembroMarketing findByEmail(String email) {
        Optional<MembroMarketing> optional = this.repository.findByEmail(email);

        if (optional.isPresent()) {
            MembroMarketing membro = optional.get();
            logger.info("Membro de Marketing:" + membro.getEmail() + " listado -- Adminitrador: " + getLogado());

            return membro;

        } else {
            logger.error("Membro não encontrado!  -- Adminitrador: " + getLogado());

            return null;
        }
    }

    @Override
    public MembroMarketing updateByEmail(String email) {
        Optional<MembroMarketing> optional = this.repository.findByEmail(email);

        if (optional.isPresent()) {
            MembroMarketing membro = optional.get();

            logger.info("Membro de Marketing: "+membro.getEmail()+" Alterado -- Adminitrador: "+getLogado());

            return this.repository.save(membro);

        } else {
            logger.info("Membro não econtrado! -- Adminitrador: "+getLogado());

            return null;
        }
    }

    private String getLogado() {
        Authentication adminLogado = SecurityContextHolder.getContext().getAuthentication();
        if (!(adminLogado instanceof AnonymousAuthenticationToken)) {
            return adminLogado.getName();
        }
        return "null";

    }


}