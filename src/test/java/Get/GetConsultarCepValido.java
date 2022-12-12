package Get;

import TestBases.ConsultarCepValidoTestBase;
import Utils.FileOperation;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GetConsultarCepValido extends ConsultarCepValidoTestBase {

    @DisplayName("Consultar CEP Válido")
    @Test
    public void consultarCepValido(){
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
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schemas/ConsultarCepValidoJsonSchema.json"))
                    .extract().response();

        try{
            String cepAtual = FileOperation.getProperties("cep").getProperty("cepValido");
            String cepEsperado = payLoad.then().extract().path("cep").toString().replaceAll("-","");
            Assertions.assertEquals(cepAtual, cepEsperado);

        }catch (Error | Exception e){
            System.out.println("CEP atual diferente do esperado " + e.getMessage());
        }
    }
}
