package br.api.hallel.moduloAPI.security;

public class SecurityRoutesConfig {

    private static final String[] FOR_ALL_DONATIONS_ROUTES = {
            "/api/doacao", "/api/doacao/objeto",
            "api/doacao/{idDoacao}/finalizar"};

    private static final String[] FOR_USER_DONATIONS_ROUTES = {
            "/api/doacao/{idMembro}/membro", "/api/doacao/membro",
            "/api/doacao/membro/objeto", "/api/doacao/evento",
            "/api/doacao/evento/objeto"
    };

    private static final String[] FOR_ASSOCIADO_DONATIONS_ROUTES = {
            "/api/doacao/retiro", "/api/doacao/retiro/objeto",
            };
}
