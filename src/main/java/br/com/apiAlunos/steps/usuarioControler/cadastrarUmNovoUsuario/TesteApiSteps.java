package br.com.apiAlunos.steps.usuarioControler.cadastrarUmNovoUsuario;

import br.com.apiAlunos.model.usuarioControler.cadastrarUmNovoUsuario.request.CadastrarUmNovoUsuarioRequestModel;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.config;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TesteApiSteps {

    Response validarResponse;

    @When("preencho os campos obrigatórios do endpoint com os seguintes dados:")
    public void preencho_os_campos_obrigatórios_do_endpoint_com_os_seguintes_dados(io.cucumber.datatable.DataTable dataTable) {

        Map<String, String> dados1 = dataTable.asMap(String.class, String.class);
        String cpf = dados1.get("cpf");

        Response deleteResponse = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .delete("/api/usuarios/deletar/" + cpf)
                .prettyPeek();

        Map<String, String> dados = dataTable.asMap(String.class, String.class);

        CadastrarUmNovoUsuarioRequestModel cadastrarUmNovoUsuarioRequestModel = new CadastrarUmNovoUsuarioRequestModel();
        cadastrarUmNovoUsuarioRequestModel.setNomeCompleto(dados.get("nomeCompleto"));
        cadastrarUmNovoUsuarioRequestModel.setEmail(dados.get("email"));
        cadastrarUmNovoUsuarioRequestModel.setCpf(dados.get("cpf"));
        cadastrarUmNovoUsuarioRequestModel.setSenha(dados.get("senha"));

        validarResponse = RestAssured
                .given()
                .config(config()
                        .encoderConfig(encoderConfig()
                                .appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(cadastrarUmNovoUsuarioRequestModel)
                .when()
                .post("/api/usuarios/cadastrar");

    }

    @When("preencho os campos obrigatórios do endpoint com os seguintes dados de um usuario ja cadastrado:")
    public void preencho_os_campos_obrigatórios_do_endpoint_com_os_seguintes_dados_de_um_usuario_ja_cadastrado(io.cucumber.datatable.DataTable dataTable) {

        Map<String, String> dados = dataTable.asMap(String.class, String.class);

        CadastrarUmNovoUsuarioRequestModel cadastrarUmNovoUsuarioRequestModel = new CadastrarUmNovoUsuarioRequestModel();
        cadastrarUmNovoUsuarioRequestModel.setNomeCompleto(dados.get("nomeCompleto"));
        cadastrarUmNovoUsuarioRequestModel.setEmail(dados.get("email"));
        cadastrarUmNovoUsuarioRequestModel.setCpf(dados.get("cpf"));
        cadastrarUmNovoUsuarioRequestModel.setSenha(dados.get("senha"));

        validarResponse = RestAssured
                .given()
                .config(config()
                        .encoderConfig(encoderConfig()
                                .appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(cadastrarUmNovoUsuarioRequestModel)
                .when()
                .post("/api/usuarios/cadastrar");
    }

    @Then("a API deverá retornar status code {int}")
    public void a_api_deverá_retornar_status_code(Integer statusCodeEsperado) {

        assertEquals(statusCodeEsperado.intValue(), validarResponse.getStatusCode(),
                "Status code retornado pela API está diferente do esperado.");

    }

    @Then("o corpo da resposta devera conter a mensagem de erro {string}")
    public void o_corpo_da_resposta_devera_conter_a_mensagem_de_erro(String mesagem) {

        String mensagemRecebida = validarResponse.jsonPath().getString("mensagem");

    }


}
