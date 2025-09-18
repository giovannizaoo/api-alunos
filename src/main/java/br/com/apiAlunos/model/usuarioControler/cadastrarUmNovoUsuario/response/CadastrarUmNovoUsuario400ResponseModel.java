package br.com.apiAlunos.model.usuarioControler.cadastrarUmNovoUsuario.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "mensagem"
})
public class CadastrarUmNovoUsuario400ResponseModel {

    @JsonProperty("mensagem")
    private String mensagem;

    @JsonProperty("mensagem")
    public String getMensagem() {
        return mensagem;
    }

    @JsonProperty("mensagem")
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}