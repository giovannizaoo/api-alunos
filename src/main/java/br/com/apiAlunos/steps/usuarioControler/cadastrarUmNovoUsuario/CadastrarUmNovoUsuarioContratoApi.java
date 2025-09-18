package br.com.apiAlunos.steps.usuarioControler.cadastrarUmNovoUsuario;

import br.com.apiAlunos.model.usuarioControler.cadastrarUmNovoUsuario.request.CadastrarUmNovoUsuarioRequestModel;
import br.com.apiAlunos.model.usuarioControler.cadastrarUmNovoUsuario.response.CadastrarUmNovoUsuario200ResponseModel;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import io.restassured.response.Response;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CadastrarUmNovoUsuarioContratoApi {

    Response validarResponse;

    CadastrarUmNovoUsuario200ResponseModel cadastrarUmNovoUsuario200ResponseModel;

    @Then("valido o contrato da API do enpoint cadastrar um novo usuario devera retornar status code {int} com os dados esperados no corpo da resposta")
    public void valido_o_contrato_da_api_do_enpoint_cadastrar_um_novo_usuario_devera_retornar_status_code_com_os_dados_esperados_no_corpo_da_resposta(Integer statusCode) {

        validarResponse = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .delete("/api/usuarios/deletar/21731682077")
                .prettyPeek();

        CadastrarUmNovoUsuarioRequestModel cadastrarUmNovoUsuarioRequestModel = new CadastrarUmNovoUsuarioRequestModel();
        cadastrarUmNovoUsuarioRequestModel.setNomeCompleto("João Silva");
        cadastrarUmNovoUsuarioRequestModel.setSenha("Hbda667788!");
        cadastrarUmNovoUsuarioRequestModel.setCpf("21731682077");
        cadastrarUmNovoUsuarioRequestModel.setEmail("joaosilva2020@e2etreinamentos.com.br");

        validarResponse = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(cadastrarUmNovoUsuarioRequestModel)
                .post("/api/usuarios/cadastrar")
                .prettyPeek();

        cadastrarUmNovoUsuario200ResponseModel = validarResponse.as(CadastrarUmNovoUsuario200ResponseModel.class);

        validarResponse.then().assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/usuarioControler/EndpointCadastrarUmNovoUsuarioStatusCode200.json"));
    }

}
