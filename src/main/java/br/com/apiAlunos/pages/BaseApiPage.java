package br.com.apiAlunos.pages;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;

public class BaseApiPage {

    @Given("que seja iniciada a chamada a url base da funcionalidade api alunos")
    public void que_seja_iniciada_a_chamada_a_url_base_da_funcionalidade_api_alunos() {

        RestAssured.baseURI = "https://plataforma.dev.e2etreinamentos.com.br";

    }
}
