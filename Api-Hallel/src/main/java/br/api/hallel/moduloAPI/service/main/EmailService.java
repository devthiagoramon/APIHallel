package br.api.hallel.moduloAPI.service.main;

import br.api.hallel.moduloAPI.model.Associado;
import br.api.hallel.moduloAPI.model.Doacao;
import br.api.hallel.moduloAPI.model.DoacaoObjeto;
import br.api.hallel.moduloAPI.payload.requerimento.EmailRequest;
import br.api.hallel.moduloAPI.repository.AssociadoRepository;
import br.api.hallel.moduloAPI.repository.DoacaoObjetoRepository;
import br.api.hallel.moduloAPI.repository.DoacaoRepository;
import br.api.hallel.moduloAPI.service.interfaces.EmailInterface;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class EmailService implements EmailInterface {

    private final JavaMailSender javaMailSender;
    @Autowired
    private AssociadoRepository associadoRepository;
    private DoacaoRepository doacaoRepository;
    private DoacaoObjetoRepository doacaoObjetoRepository;

    public EmailService(final JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendMail(EmailRequest request) {
        var mensagem = new SimpleMailMessage();
        var allAssociados = this.associadoRepository.findAll();

        allAssociados.forEach(associado -> {
            if (verifiqAniversario(associado)) {

                mensagem.setTo(associado.getEmail());
                mensagem.setSubject("Parabéns, " + associado.getNome() + "!");
                mensagem.setText(request.getConteudo());

                javaMailSender.send(mensagem);
                log.info("EMAIL ENVIADO!");

            }
        });

    }

    @Override
    public void sendMailAttachment(EmailRequest request) throws MessagingException {

        var mimeMessage = javaMailSender.createMimeMessage();
        var helper = new MimeMessageHelper(mimeMessage, true);
        var allAssociados = this.associadoRepository.findAll();

        allAssociados.forEach(associado -> {
            if (verifiqAniversario(associado)) {
                try {
                    helper.setTo(associado.getEmail());
                    helper.setSubject("Parabéns, " + associado.getNome() + "!");
                    helper.setText(request.getConteudo(), true);

                    javaMailSender.send(mimeMessage);
                    log.info("EMAIL ENVIADO COM ANEXO!");

                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }

            }
        });

    }

    private String getDataAtual() {
        Date dataAtual = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM");

        return format.format(dataAtual);
    }

    private Boolean verifiqAniversario(Associado associado) {

        if (associado.getDataNascimento() != null) {


            LocalDate dataNascimento = associado.getDataNascimento().toInstant().
                    atZone(ZoneId.of("America/Puerto_Rico")).toLocalDate();
            LocalDate dateAtual = new Date().toInstant().
                    atZone(ZoneId.of("America/Puerto_Rico")).toLocalDate();

            log.info("Data de nascimento do usuário '"+associado.getNome()+"' : "+dataNascimento);
            log.info("Data atual: "+dateAtual);

            if (dateAtual.getDayOfMonth() == dataNascimento.getDayOfMonth()
                    && dateAtual.getMonthValue() == dataNascimento.getMonthValue()) {
                log.info("Aniversariante: " + associado.getNome());
                return true;
            }
        }

        return false;
    }

    public void EmailDeDoacao(String toEmail, String subject,Associado associado){



        SimpleMailMessage menssagem = new SimpleMailMessage();
        menssagem.setFrom("miguelimabr10@gmail.com");
        menssagem.setTo(toEmail);


            menssagem.setText("Prezado(a) "+associado.getNome()+",\n" +
                    "\n" +
                    "Espero que esta mensagem o(a) encontre bem. Gostaríamos de expressar nossa sincera gratidão pela generosa doação que você fez para a comunidade HALLEL. Sua contribuição é um passo significativo para nos ajudar a alcançar nossos objetivos e continuar nosso trabalho em prol de uma causa tão importante.\n" +
                    "\n" +
                    "Sua doação não só demonstra seu compromisso com nossa causa, mas também nos dá o impulso necessário para continuar fazendo a diferença em nossa comunidade. Graças à sua generosidade, poderemos ampliar nossos programas e serviços, impactando positivamente a vida de muitas pessoas que mais precisam.\n" +
                    "\n" +
                    "Gostaríamos de manter você informado sobre o impacto de sua doação e os projetos que estamos realizando. No futuro, compartilharemos atualizações regulares para mostrar como sua contribuição está fazendo a diferença real nas vidas das pessoas que servimos.\n" +
                    "\n" +
                    "Mais uma vez, muito obrigado por acreditar em nossa missão e por sua contribuição generosa. Sua ajuda é fundamental para o sucesso da nossa comunidade HALLEL. Estamos profundamente gratos por sua parceria.\n" +
                    "\n" +
                    "Se você tiver alguma dúvida ou desejar mais informações sobre nossos projetos, não hesite em entrar em contato conosco a qualquer momento.");


        menssagem.setSubject(subject);


        javaMailSender.send(menssagem);

        log.info("Email enviado com sucesso");


    }


    public Boolean VerificaDoacao(List<Doacao> doacoes, List<DoacaoObjeto> doacoesObjetos) {
        LocalDate dataAtual = LocalDate.now();

        // Verifique as doações regulares
        if (!doacoes.isEmpty()) {
            doacoes.sort(Comparator.comparing(Doacao::getDataDoacao).reversed());
            LocalDate dataDaUltimaDoacao = LocalDate.parse(doacoes.get(0).getDataDoacao(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            long diferencaDias = ChronoUnit.DAYS.between(dataDaUltimaDoacao, dataAtual);

            if (diferencaDias <= 30) {
                return true;
            }
        }

        // Verifique as doações de objetos
        if (!doacoesObjetos.isEmpty()) {
            doacoesObjetos.sort(Comparator.comparing(DoacaoObjeto::getDataDoacao).reversed());
            LocalDate dataDaUltimaDoacaoObjeto = LocalDate.parse((CharSequence) doacoesObjetos.get(0).getDataDoacao(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            long diferencaDiasObjeto = ChronoUnit.DAYS.between(dataDaUltimaDoacaoObjeto, dataAtual);

            if (diferencaDiasObjeto <= 30) {
                return true;
            }
        }

        return false;
    }


    public void EmailDeAvisoDeDoacao(String toEmail, String subject){


        var allAssociados = this.associadoRepository.findAll();
        SimpleMailMessage menssagem = new SimpleMailMessage();
        menssagem.setFrom("miguelimabr10@gmail.com");
        menssagem.setTo(toEmail);
        allAssociados.forEach(associado -> {

            List<Doacao> doacoes = doacaoRepository.findByEmailDoador(associado.getEmail());

            List<DoacaoObjeto> doacoesObjetos = doacaoObjetoRepository.findByEmailDoador(associado.getEmail());



        if(VerificaDoacao(doacoes,doacoesObjetos)) {

            menssagem.setText("qual é seu vacilão porque não doou esse mês ?");

        }

    });

        menssagem.setSubject(subject);


        javaMailSender.send(menssagem);

        log.info("Email enviado com sucesso");

    }



}

