package br.com.apiAlunos.steps.usuarioControler.cadastrarUmNovoUsuario;

import br.com.apiAlunos.model.usuarioControler.cadastrarUmNovoUsuario.request.CadastrarUmNovoUsuarioRequestModel;
import br.com.apiAlunos.model.usuarioControler.cadastrarUmNovoUsuario.response.CadastrarUmNovoUsuario200ResponseModel;
import br.com.apiAlunos.model.usuarioControler.cadastrarUmNovoUsuario.response.CadastrarUmNovoUsuario400ResponseModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.config;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static org.junit.Assert.*;

public class CadastrarUmNovoUsuarioApiSteps {

    Response validarResponse;

    CadastrarUmNovoUsuario200ResponseModel cadastrarUmNovoUsuario200ResponseModel;

    CadastrarUmNovoUsuario400ResponseModel cadastrarUmNovoUsuario400ResponseModel;

    @When("preencho todos os campos obrigatorios do endpoint cadastrar um novo usuario")
    public void preencho_todos_os_campos_obrigatorios_do_endpoint_cadastrar_um_novo_usuario() {

        validarResponse = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .delete("/api/usuarios/deletar/21731682077")
                .prettyPeek();

        CadastrarUmNovoUsuarioRequestModel cadastrarUmNovoUsuarioRequestModel = new CadastrarUmNovoUsuarioRequestModel();
        cadastrarUmNovoUsuarioRequestModel.setNomeCompleto("João Silva");
        cadastrarUmNovoUsuarioRequestModel.setSenha("Hbda667788!");
        cadastrarUmNovoUsuarioRequestModel.setCpf("21731682077");
        cadastrarUmNovoUsuarioRequestModel.setEmail("joao.silva@e2etreinamentos.com.br");

        validarResponse = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(cadastrarUmNovoUsuarioRequestModel)
                .post("/api/usuarios/cadastrar")
                .prettyPeek();

        cadastrarUmNovoUsuario200ResponseModel = validarResponse.as(CadastrarUmNovoUsuario200ResponseModel.class);

    }

    @When("preencho todos os campos obrigatorios do endpoint cadastrar um novo usuario utilizando um cpf ja cadastrado em outro usuario")
    public void preencho_todos_os_campos_obrigatorios_do_endpoint_cadastrar_um_novo_usuario_utilizando_um_cpf_ja_cadastrado_em_outro_usuario() {

        CadastrarUmNovoUsuarioRequestModel cadastrarUmNovoUsuarioRequestModel = new CadastrarUmNovoUsuarioRequestModel();
        cadastrarUmNovoUsuarioRequestModel.setNomeCompleto("João Silva");
        cadastrarUmNovoUsuarioRequestModel.setSenha("Hbda667788!");
        cadastrarUmNovoUsuarioRequestModel.setCpf("21731682077");
        cadastrarUmNovoUsuarioRequestModel.setEmail("joao.silva@e2etreinamentos.com.br");

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
//                .then()
//                .statusCode(400)
//                .extract()
//                .response();

//        cadastrarUmNovoUsuario400ResponseModel = validarResponse.as(CadastrarUmNovoUsuario400ResponseModel.class);

    }

    @Then("a API do enpoint cadastrar um novo usuario devera retornar status code {int} com os dados esperados no corpo da resposta")
    public void a_api_do_enpoint_cadastrar_um_novo_usuario_devera_retornar_status_code_com_os_dados_esperados_no_corpo_da_resposta(Integer statusCodeEsperado) {

        // Valida o status code
        assertEquals((int) statusCodeEsperado, validarResponse.getStatusCode());

        if (statusCodeEsperado == 200) {
            // Desserializa o corpo da resposta de sucesso
            CadastrarUmNovoUsuario200ResponseModel cadastrarUmNovoUsuario200ResponseModel1 =
                    validarResponse.as(CadastrarUmNovoUsuario200ResponseModel.class);

            // Valida os dados retornados
            assertEquals("João Silva", cadastrarUmNovoUsuario200ResponseModel1.getNomeCompleto());
            assertEquals("joao.silva@e2etreinamentos.com.br", cadastrarUmNovoUsuario200ResponseModel1.getEmail());
            assertEquals("21731682077", cadastrarUmNovoUsuario200ResponseModel1.getCpf());

            // Validação segura da senha criptografada
            assertNotNull(cadastrarUmNovoUsuario200ResponseModel1.getSenha());
            assertTrue(cadastrarUmNovoUsuario200ResponseModel1.getSenha().startsWith("$2a$"));

//        } else if (statusCodeEsperado == 400) {
//
//            // Desserializa o corpo da resposta de erro
//            CadastrarUmNovoUsuario400ResponseModel cadastrarUmNovoUsuario400ResponseModel1 =
//                    validarResponse.as(CadastrarUmNovoUsuario400ResponseModel.class);
//
//            String mensagem = validarResponse.jsonPath().getString("mensagem");
//            assertEquals("CPF já cadastrado.", mensagem);
//
//            // Valida a mensagem de erro
//            assertEquals("CPF já cadastrado.", cadastrarUmNovoUsuario400ResponseModel1.getMensagem());
        }
    }

    @Then("a API do enpoint cadastrar um novo usuario devera retornar status code {int} com os dados esperados no corpo da resposta negativo")
    public void a_api_do_enpoint_cadastrar_um_novo_usuario_devera_retornar_status_code_com_os_dados_esperados_no_corpo_da_resposta_negativo(Integer statusCodeEsperado) throws JsonProcessingException {


        assertEquals((int) statusCodeEsperado, validarResponse.getStatusCode());

        String json = validarResponse.getBody().asString();

        ObjectMapper mapper = new ObjectMapper();
        CadastrarUmNovoUsuario400ResponseModel erro = mapper.readValue(json, CadastrarUmNovoUsuario400ResponseModel.class);

        assertEquals("CPF já cadastrado.", erro.getMensagem());


    }

}


