package Get;

import TestBases.ConsultarCepInexistenteTestBase;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GetConsultarCepInexistente extends ConsultarCepInexistenteTestBase {

    @DisplayName("Consultar CEP Inexistente")
    @Test
    public void consultarCepInexistente() {
        Response payLoad =
                given()
                        .spec(requestSpec)
                .when()
                        .get()
                .then()
                    .log().all()
                    .spec(responseSpec)
                .and()
                    .log()
                    .body()
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schemas/ConsultarCepInexistenteJsonSchema.json"))
                    .extract().response();

        Assertions.assertEquals(true, payLoad.body().path("erro"));
    }
}
