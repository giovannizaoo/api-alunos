package br.com.apiAlunos.model.usuarioControler.cadastrarUmNovoUsuario.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "nomeCompleto",
        "cpf",
        "email",
        "senha"
})
public class CadastrarUmNovoUsuario200ResponseModel {

    @JsonProperty("nomeCompleto")
    private String nomeCompleto;

    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("email")
    private String email;

    @JsonProperty("senha")
    private String senha;

    @JsonProperty("nomeCompleto")
    public String getNomeCompleto() {
        return nomeCompleto;
    }

    @JsonProperty("nomeCompleto")
    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    @JsonProperty("cpf")
    public String getCpf() {
        return cpf;
    }

    @JsonProperty("cpf")
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("senha")
    public String getSenha() {
        return senha;
    }

    @JsonProperty("senha")
    public void setSenha(String senha) {
        this.senha = senha;
    }

}
