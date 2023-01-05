package br.api.hallel.payload.resposta;

public class MessageResposta {

    private String message;

    public MessageResposta(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
