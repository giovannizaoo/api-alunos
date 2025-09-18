package br.com.apiAlunos.usuarioControler;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/usuarioControler/",
        glue = {
                "br.com.apiAlunos.steps.usuarioControler",
                "br.com.apiAlunos.pages" // Inclui a pasta onde está o método com @Given
        },
        plugin = {"pretty",
                "html:target/cucumber-report.html",
                "json:target/cucumber-report.json",
        },

        monochrome = true
)
public class UsuarioControlerTest {

}
