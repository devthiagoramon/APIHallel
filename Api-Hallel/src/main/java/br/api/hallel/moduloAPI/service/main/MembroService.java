package br.api.hallel.moduloAPI.service.main;

import br.api.hallel.moduloAPI.model.Membro;
import br.api.hallel.moduloAPI.payload.requerimento.EditarPerfilMembroReq;
import br.api.hallel.moduloAPI.payload.resposta.MembroResponse;
import br.api.hallel.moduloAPI.payload.resposta.PerfilResponse;
import br.api.hallel.moduloAPI.repository.ContribuicaoEventoRepository;
import br.api.hallel.moduloAPI.repository.EventosRepository;
import br.api.hallel.moduloAPI.repository.MembroRepository;
import br.api.hallel.moduloAPI.service.interfaces.MembroInterface;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class MembroService implements MembroInterface {

    @Autowired
    private MembroRepository repository;
    @Autowired
    private EventosRepository eventosRepository;
    @Autowired
    private ContribuicaoEventoRepository contRepository;

    //Método para criptografar senha
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //CRUD DE MEMBRO

    @Override
    public Membro createMembro(Membro membro) {
        System.out.println("Criando membro");
        String encoder = this.passwordEncoder()
                             .encode(membro.getSenha()); //CRIPTOGRAFA A SENHA
        membro.setSenha(encoder);
        return this.repository.insert(membro);
    }

    @Override
    public List<MembroResponse> listAllMembros() {
        List<MembroResponse> responseList = new ArrayList<>();

        for (Membro membros : this.repository.findAll()) {
            responseList.add(new MembroResponse().toList(membros));
        }

        return responseList;
    }

    //Método para listar todos os membros
    @Override
    public List<MembroResponse> listAllMembros(Pageable pageable) {
        List<MembroResponse> responseList = new ArrayList<>();

        for (Membro membros : this.repository.findAll(pageable)) {
            responseList.add(new MembroResponse().toList(membros));
        }

        return responseList;
    }

    //Método para pegar/listar um membro tendo como parâmetro seu Id
    @Override
    public Membro listMembroId(String id) {
        Optional<Membro> membroOptional = this.repository.findById(id);
        if (membroOptional.isPresent()) {
            return membroOptional.get();
        } else {
            log.info("Membro com id: '" + id + "' não enctrado");
            return null;
        }
    }

    @Override
    public Membro updateMembro(String idMembro,
                               Membro membroRequest) {
        Membro membro = membroRequest;
        membro.setId(idMembro);
        log.info("Atualizando o membro Id(" + idMembro + ")");

        return this.repository.save(membro);
    }

    //Atualizar Informações do Membro pelo id
    @Override
    public Membro updatePerfilMembro(String id, Membro membroModel) {
        //Busca membro pelo Id no banco de dados
        Optional<Membro> optional = this.repository.findById(id);

        //verifica se o membro existe no banco de dados
        if (optional.isPresent()) {
            //Se sim, pega o objeto do membro
            Membro membro = optional.get();
            membro.setNome(membroModel.getNome());
            membro.setIdade(membroModel.getIdade());
            membro.setEmail(membroModel.getEmail());
            String encoder = this.passwordEncoder()
                                 .encode(membro.getSenha());
            membro.setSenha(encoder);
            membro.setDataNascimento(membroModel.getDataNascimento());

            //atualiza no banco de dados
            return this.repository.save(membroModel);
        }

        //caso não exista o membro com o deivido Id, retorna nada
        return null;
    }

    //Deletar o membro pelo ID
    @Override
    public void deleteMembroById(String id) {
        //Busca membro pelo Id no banco de dados
        Optional<Membro> optional = this.repository.findById(id);

        //Verifica se existe no banco de dados
        if (optional.isPresent()) {
            //caso exista, deleta o membro pelo seu Id
            this.repository.deleteById(id);
        }

    }

    //Método para buscar um membro no banco por seu Email e Senha   .
    @Override
    public Membro findByEmailAndPassword(String email, String senha) {
        //Busca membro pelo Id no banco de dados
        Optional<Membro> optional = this.repository.findByEmailAndSenha(email, senha);

        //Verifica se existe no banco de dados
        if (optional.isPresent()) {
            //Se sim, pega o objeto de membro
            Membro membro = optional.get();

            //retorna esse membro
            return membro;
        } else {
            throw new IllegalArgumentException("Usuario com email " + email + " não foi encontrado");
        }
    }


    @Override
    public Membro findByEmail(String email) {
        return this.repository.findByEmail(email)
                              .isPresent() ? this.repository.findByEmail(email)
                                                            .get() : null;
    }

    //INFORMAÇÕES PARA PODER O USUÁRIO VISUALIZAR SEU PERFIL
    @Override
    public PerfilResponse visualizarPerfil(String id) throws
            IllegalAccessException {

        //FAZ A BUSCA DO MEMBRO PELO BD, UTILIZANDO SEU ID
        Optional<Membro> optional = this.repository.findById(id);
        if (optional.isPresent()) {

            //SE EXISTE, EXIBE AS INFORMAÇÕES DO PERFIL
            Membro membro = optional.get();
            return new PerfilResponse().toPerfilResponse(membro);
        }
        throw new IllegalAccessException("Usuario não encontrado para carregar o perfil");
    }

    @Override
    public PerfilResponse visualizarPerfilPeloToken(
            String token) throws
            IllegalAccessException {
        Optional<Membro> optional = this.repository.findByToken(token);
        if (optional.isEmpty()) {
            throw new IllegalAccessException("Usuário não encontrado");
        }
        Membro membro = optional.get();
        return new PerfilResponse().toPerfilResponse(membro);
    }

    @Override
    public PerfilResponse editarPerfilMembro(
            EditarPerfilMembroReq dto) throws IllegalAccessException {
        Optional<Membro> optionalMembro = this.repository.findById(dto.getId());

        if (optionalMembro.isEmpty()) {
            throw new IllegalAccessException("Usuário não encontrado");
        }

        Membro newMembro = dto.toMembro();
        Membro oldMembro = optionalMembro.get();

        oldMembro.setNome(newMembro.getNome());
        oldMembro.setDataNascimento(newMembro.getDataNascimento());
        oldMembro.setImage(newMembro.getImage());
        oldMembro.setCpf(newMembro.getCpf());
        oldMembro.setEmail(newMembro.getEmail());
        oldMembro.setTelefone(newMembro.getTelefone());

        this.repository.save(oldMembro);

        return new PerfilResponse().toPerfilResponse(oldMembro);
    }

    @Override
    public List<MembroResponse> listByPage(int pagina) {
        Pageable pageable = PageRequest.of(pagina, 15);

        List<MembroResponse> responseList = new ArrayList<>();

        for (Membro membro : this.repository.findAll(pageable)) {
            responseList.add(new MembroResponse().toResponse(membro));
        }
        return responseList;
    }

  /*  @Override
    public Boolean enviarContribuicaoEvento(String idEvento, ContribuicaoEventoReq contEventoReq) {
        Eventos eventos = this.eventosRepository.findById(idEvento).get();

        if (eventos.getContribuicaoEventoList() == null) {
            List<ContribuicaoEvento> list = new ArrayList<>();
            list.add(contEventoReq.toContribuicaoEvento());
            eventos.setContribuicaoEventoList(list);

        } else {
            eventos.getContribuicaoEventoList().add(contEventoReq.toContribuicaoEvento());
        }

        contEventoReq.setEventos(new EventosRequest().toEventoRequest(eventos));
        this.contRepository.save(contEventoReq.toContribuicaoEvento());
        this.eventosRepository.save(eventos);

        return true;
    }

*/
}